<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head> 
<body>

<script>

	var tab_size = 0;

</script>
<input type="hidden" id="tab_size" name="tab_size" value="2" />
<div class="container" style="padding-right: 5px;padding-left: 5px;margin-right: 5px; margin-left: 5px;">
  <ul class="nav nav-tabs" id="navTab" style="width: 1800px">
    <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
  </ul>

  <div class="tab-content" id="tabContents"  style="width: 1800px"> 
    <div id="home" class="tab-pane fade in active" style="width: 1800px">
      <h3>HOME</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
    </div>
    <!-- <div id="menu2" class="tab-pane fade">
      <h3>Menu 2</h3>
      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
    </div>
    <div id="menu3" class="tab-pane fade">
      <h3>Menu 3</h3>
      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
    </div> -->
  </div>
</div>

</body>
</html>
