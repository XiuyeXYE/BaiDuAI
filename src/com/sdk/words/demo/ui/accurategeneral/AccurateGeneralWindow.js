var cls = Java.type("com.sdk.words.demo.ui.accurategeneral.AccurateGeneralWindow");
var sys = Java.type("java.lang.System");

print(sys.getProperty("java.class.path"));

//console.log(123);

for(var key in cls){
	print("key="+key);
}

cls.main([]);
print("end");
