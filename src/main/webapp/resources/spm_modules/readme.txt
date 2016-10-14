如何获取最新模板？
请修改build.gradle，在合适位置增加
apply plugin: "APIJarProcess"
configurations {
    jsCommonLibs
}

buildscript {
    repositories {
       maven { url "http://10.1.228.199:18081/nexus/content/groups/public/" }
    }
    dependencies {
        classpath group: 'com.ai.runner.plugin', name: 'apijarprocessplugin', version: '1.0'
    }
}

dependencies {
	jsCommonLibs 'com.ai.opt.uniframe:opt-jscommon-libs:1.0-SNAPSHOT'
	.....其它配置
}

def getJsCommonLibs=task("getJsCommonLibs",type:com.ai.runner.gradle.plugin.jar.APIJarProcessTask){
	jarPaths=configurations.jsCommonLibs.files.asType(List)
    destinationDir file("src/main/webapp/resources/spm_modules") //这里面的路径建议保持默认
}

配置好上述脚本后，请在IDE插件或者在命令行执行
gradle getJsCommonLibs 


