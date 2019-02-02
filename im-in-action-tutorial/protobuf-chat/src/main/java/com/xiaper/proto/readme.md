
## 编译
- protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/addressbook.proto
- protoc --java_out=. addressbook.proto 
