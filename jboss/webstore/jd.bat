REM javadoc -sourcepath ws-dummy/src/main/java hu.qwaevisz.webstore.dummy

REM javadoc -classpath ws-common/build/classes/main -sourcepath ws-dummy/src/main/java hu.qwaevisz.webstore.dummy

javadoc -classpath ws-common\build\classes\main -docletpath ws-doclet\build\classes\main -doclet hu.qwaevisz.webstore.doclet.WebStoreDoclet -sourcepath ws-dummy\src\main\java -ws-filename ws.xml hu.qwaevisz.webstore.dummy

REM javadoc -classpath ws-common\build\libs\ws-common.jar -docletpath ws-doclet\build\libs\ws-doclet.jar -doclet hu.qwaevisz.webstore.doclet.WebStoreDoclet -sourcepath ws-dummy\src\main\java hu.qwaevisz.webstore.dummy