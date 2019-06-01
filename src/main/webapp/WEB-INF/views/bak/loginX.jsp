<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>如何实现左边为导航栏点击后右边为连接页面</title>
    <meta charset="utf-8">
    <script type="text/javascript">
        function setTab(m,n){
            var tli=document.getElementById("leftmenu"+m).getElementsByTagName("li");
            var mli=document.getElementById("mcont"+m).getElementsByTagName("ul");
            for(i=0;i<tli.length;i++){
                tli[i].className=i==n?"hover":"";
                mli[i].style.display=i==n?"block":"none";
            }
        }
    </script>
    <style type="text/css">
        .aa{ width:120px; float:left;}
        .aa li{ padding:5px; background:#ff0000; cursor:pointer;border:1px solid;}
        .bb{ width:500px;height:200px; float:left; background:pink;}
        .bb ul li{list-style:none;}
        #leftmenu0{list-style:none;margin:0;}
    </style>
</head>
<body>
<div class="aa">
    <ul  id="leftmenu0">
        <li class="hover" onclick="setTab(0,0)">nav1</li>
        <li onclick="setTab(0,1)">nav2</li>
        <li onclick="setTab(0,2)">nav3</li>
        <li onclick="setTab(0,3)">nav4</li>
        <li onclick="setTab(0,4)">nav5</li>
    </ul>
</div>
<div id="mcont0" class="bb">
    <ul class="block" style="display: block">
        <span>content1</span>
        <li>你的内容</li></ul>
    <ul class="block" style="display: none">content2</ul>
    <ul class="block" style="display: none">content3</ul>
    <ul class="block" style="display: none">content4</ul>
    <ul class="block" style="display: none">content5</ul>
</div>
</body>
</html>
