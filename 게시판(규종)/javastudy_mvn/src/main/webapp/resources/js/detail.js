$("document").ready(()=>{
	selectComment();
	insertComment();
})

const commentForm = ({writer, content, regDate, commentNo}) => 
	`
	  <td>${writer}</td>
	  <td>${content}</td>
	  <td>${new Date(regDate).format('yyyy-MM-dd HH:mm:ss')}</td>
	  <td comment-no="${commentNo}">
	  	  <button type="button" class="deleteComment">삭제</button>	
	  	  <button type="button" class="updateFormComment">수정</button>	
	  </td>
	`;

const commentUpdateForm = ({writer, content, commentNo}) => 
	`
	  <td>${writer}</td>
	  <td>
		<textarea name="content" rows="2" cols="60">${content}</textarea>
	  </td> 
	  <td colspan="2" comment-no="${commentNo}"> 
		<button type="button" class="updateComment">수정</button> 
		<button type="button" class="cancelBtn">취소</a> 
	  </td> 
	`;

const selectComment = () => {
	$.ajax({
		url : "comment-select.do",
		data : {no : boardNo}
	}).done((result) => {
		result = Array.from(JSON.parse(result));
		
		if(result.length === 0) {
			let html = `
				<tr>
					<td colspan='4'>댓글이 존재하지 않습니다.</td>
				</tr>
			`
			$("#commentTable").html(html);
			return;
		}
		const html = result.reduce((accumulator, comment, currentIndex) => {
			const currentValue =
			`<tr>
				${commentForm({
					content : comment.content,
					regDate : comment.regDate,
					commentNo : comment.commentNo,
					writer : comment.writer})}
			 </tr>
			`;
			return accumulator + currentValue;
		}, "");
		$("#commentTable").html(html);
		eventComment();
	});
};

const insertComment = () => {
	$("#insertCommentBtn").click(function(){
		const formData = new FormData(document.getElementById('commentRegistForm'));
		let data = {};
		for(let form of formData){
			data[form[0]] = form[1];
		}
		$("#commentRegistForm input[name='writer']").val("");
		$("#commentRegistForm textarea").val("");
		$.ajax({
			url : "comment-insert.do",
			type : "POST",
			data : data
		}).done(comment => {
			comment = JSON.parse(comment);
			$("#commentTable").append(
			`<tr>
				${commentForm({
					content : comment.content,
					regDate : comment.regDate,
					commentNo : comment.commentNo,
					writer : comment.writer})}
			 </tr>
			`);
			eventDeleteComment($("#commentTable").find("tr:last-child .deleteComment"));
			eventUpdateFormComment($("#commentTable").find("tr:last-child .updateFormComment"));
		});
	});
}

const eventDeleteComment = DOM => {
	DOM.click(function(){
		const commentNo = $(this).parent().attr("comment-no");
		$.ajax({
			url : "comment-delete.do",
			data : {commentNo}
		}).done(()=>{
			$(this).parent().parent().remove();
		})
	});
}

const eventUpdateFormComment = DOM => {
	DOM.click(function(){
		const tr = $(this).parent().parent();
		const commentNo = $(this).parent().attr("comment-no");
		const writer = tr.find("td:eq(0)").text();
		const content = tr.find("td:eq(1)").text();
		
		tr.html(commentUpdateForm({content,writer,commentNo}));
		eventUpdateComment(tr.find(".updateComment"));
		eventCancelBtn(tr.find(".cancelBtn"));
	});
}

const eventUpdateComment = DOM => {
	DOM.click(function(){
		const commentNo = $(this).parent().attr("comment-no");
		const content = $(this).parent().parent().find("textarea").val();
		$.ajax({
			url : "comment-update.do",
			type : "POST",
			data : {commentNo, content}
		}).done( comment => {
			comment = JSON.parse(comment);
			const tr = $(this).parent().parent();
			const writer = tr.find("td:eq(0)").text();
			tr.html(commentForm({writer,content,regDate:comment.regDate, commentNo}));
			eventDeleteComment(tr.find(".deleteComment"))
			eventUpdateFormComment(tr.find(".updateFormComment"));
		})
	});
}

const eventCancelBtn = DOM => {
	DOM.click(function(){
		const tr = $(this).parent().parent();
		const commentNo = $(this).parent().attr("comment-no");
		const writer = tr.find("td:eq(0)").text();
		const content = tr.find("td:eq(1)").text();
		const regDate = tr.find("td:eq(2)").text();
		tr.html(commentForm({writer,content,regDate, commentNo}));
		eventDeleteComment(tr.find(".deleteComment"))
		eventUpdateFormComment(tr.find(".updateFormComment"));
	});
}

const eventComment = () => {
	eventDeleteComment($(".deleteComment"));
	eventUpdateFormComment($(".updateFormComment"));
	eventUpdateComment($(".updateComment"))
	eventCancelBtn($(".cancelBtn"));
}			


Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    const weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    let d = this;
    let h;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};
