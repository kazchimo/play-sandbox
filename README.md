# play-sandbox
## .envファイルの設定
[このplugin](https://github.com/mefellows/sbt-dotenv)通りに設定.


を実行。
その後表示されたページの入力欄でhttp://localhost:9000/swagger.jsonを入力。

## カバレッジありのテスト実行方法
```shell script
sbt clean coverage test coverageReport
```

## sbtのキーバインドをVimに変更する方法
$HOME/.inpurtrcに
```
set editing-mode vi
```
を追記
