<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <!-- google web font notosans-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

    <!-- 부트스트랩 5.3.3-->
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        body{
            font-family: "Noto Sans KR", sans-serif;
            font-optical-sizing: auto;
            font-weight: 400;
            font-style: normal;
        }

        a{
            text-decoration: none;
            color: black;
        }

        ul, li, ol{
            list-style: none;
            margin: 0px;
            padding: 0px;
        }

        h1{
            padding: 24px 0;
        }

        .login-area {
            display: flex;
            align-items: center;
            justify-content: flex-end;
        }

        .login-area input[type="button"],
        .login-area input[type="submit"]{
            width: 50%;
            float: left;
        }

        .logout-area > div{
            display: flex;
            justify-content: flex-start;
            align-items: center;
            padding: 12px 0;
            gap: 12px;
        }

        .main-nav{
            background: black;
        }

        .main-nav ul{
            display: flex;
            flex-direction: row;
            
        }

        .main-nav ul li{
            width: 150px;
            height: 50px;
        }

        .main-nav ul li a{
            color: white;
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            width: 100%;
            height: 100%;
            line-height: 50px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <h1 align="center">Welcome KH World</h1>
    <div class="login-area">
        <!--로그인 전-->
        <form action="">
                <table>
                    <tr>
                        <th>아이디</th>
                        <td><input type="text" name="userId" required></td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td><input type="password" name="userPwd" required></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="로그인">
                            <input type="button" value="회원가입">
                        </td>
                    </tr>
                </table>
        </form>
    </div>
        <!--로그인 후-->
    <!-- <div class="logout-area">
        <b>최지원님</b>의 방문을 환영합니다.. <br><br>
        <div>
            <a href="">마이페이지</a>
            <a href="">로그아웃</a>
        </div>
    </div> -->

    <nav class="main-nav">
        <ul>
            <li><a href="">HOME</a></li>
            <li><a href="">공지사항</a></li>
            <li><a href="">일반게시판</a></li>
            <li><a href="">사진게시판</a></li>
        </ul>
    </nav>
</body>
</html>