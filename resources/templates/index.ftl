<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Ktor 範例程式</title>

    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,500,700" rel="stylesheet" type="text/css">
    <link href="/static/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <script src="/static/js/jquery-2.2.3.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>

    <style>
        body {
            font-family: 'Raleway';
            margin-top: 25px;
        }

        button .fa {
            margin-right: 6px;
        }

        .table-text div {
            padding-top: 6px;
        }

        .form-inline {
            display: inline;
        }

        .check {
            color: #ccc;
            text-decoration: line-through;
        }
    </style>

    <script>
        $(function () {
            $('#task-name').focus();
        });
    </script>
</head>

<body>
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <a class="navbar-brand" href="#">Task 清單</a>
            </div>

            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    &nbsp;
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="container">
    <div class="col-sm-offset-2 col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                建立新 Task
            </div>

            <div class="panel-body">
                <!-- 建立新 Task 表單 -->
                <form action="/tasks" method="POST" class="form-horizontal">
                    <!-- Task 名稱 -->
                    <div class="form-group">
                        <label for="task-name" class="col-sm-3 control-label">Task</label>

                        <div class="col-sm-6">
                            <input type="text" name="title" id="task-name" class="form-control" value="">
                        </div>
                    </div>

                    <!-- 建立新 Task 按鈕 -->
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-6">
                            <button type="submit" class="btn btn-default">
                                <i class="fa fa-plus"></i>建立 Task
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- Tasks 清單 -->
        <div class="panel panel-default">
            <div class="panel-heading">
                所有 Task 清單
            </div>

            <div class="panel-body">
                <table class="table table-striped task-table">
                    <thead>
                    <tr>
                        <th>Task</th>
                        <th width="200">&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#-- @ftlvariable name="task" type="io.ktor.web.entities.Task" -->
                    <#list tasks as task>
                    <tr>
                        <td class="table-text">
                            <div class="${task.completed?then('check', '')}">
                                ${task.title}
                            </div>
                        </td>
                        <td>
                            <#if !task.completed >
                            <!-- 完成 Task 按鈕 -->
                            <form action="/tasks/${task.id}/completed" method="POST" style="display: inline-block">
                                <button type="submit" class="btn btn-success">
                                    <i class="fa fa-check"></i>完成
                                </button>
                            </form>
                            </#if>
                            <!-- 刪除 Task 按鈕 -->
                            <form action="/tasks/${task.id}/delete" method="POST" style="display: inline-block">
                                <button type="submit" class="btn btn-danger">
                                    <i class="fa fa-trash"></i>刪除
                                </button>
                            </form>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
