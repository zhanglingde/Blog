/*
 *    	先实现方法：getContent(),并获取到总个数
 *		页面有  <div id="paging"> </div> 
 *			
 */

// 当前页面
var pageIndex = 1;
// 总个数
var allCount = 0;
// 一页显示的个数
var pageNum = 10;	

function nextPage() {
	pageIndex++;
	// 最大页面数
	var maxPage = (allCount + pageNum-1)/pageNum; 
	maxPage = parseInt(maxPage);
	if (pageIndex > maxPage) {
		pageIndex = maxPage;
		return;
	}
	getContent();
	updatePaging();
}
function lastPage() {
	pageIndex--;
	if (pageIndex < 1) {
		pageIndex = 1;
		return;
	}
	getContent();
	updatePaging();
}
function gotoPage(i) {
	pageIndex = i;
	if (pageIndex < 1) {
		pageIndex = 1;
		return;
	}
	// 最大页面数
	var maxPage = (allCount + pageNum-1)/pageNum; 
	maxPage = parseInt(maxPage);
	if (pageIndex > maxPage) {
		pageIndex = maxPage;
		return;
	}
	getContent();
	updatePaging();
}

function updatePaging() {
	// 最大页面数
	var maxPage = (allCount + pageNum-1)/pageNum;  
	maxPage = parseInt(maxPage);
	
	// 拼接页面  
	var str = "";
	if(pageIndex > 1) str += "<a href='javascript:lastPage()'>上一页 </a> "
	if(pageIndex < maxPage)str += "<a href='javascript:nextPage()'>下一页 </a> ";
	
	// 确定显示的第一页
	var beginP = pageIndex - 2;
	if (beginP > maxPage - 4) beginP = maxPage - 4;
	if (beginP <= 0) beginP = 1;
	console.log(beginP);
	// 添加第几页跳转
	for (var i = 0; i < maxPage && i < 5; i++) {
		if (beginP+i != pageIndex)
			str += "<a href='javascript:gotoPage(" + (beginP + i) + ")'>"
					+ (beginP + i) + "</a> ";
		else
			str += "<a>" + (beginP + i) + "</a> ";
	}
	// 添加总记录跳转 
	str += "共" + allCount + "条记录";
	$("#paging").html(str);
}
