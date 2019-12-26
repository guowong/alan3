var PLATFORM_IDS         = new Array();
var PLATFORM_LONG_NAMES  = new Array();

var platformSelectIndex = 0;
var platformId = "";
var platformName = "";

PLATFORM_IDS[0] = "";
PLATFORM_IDS[1] = "linux";
PLATFORM_IDS[2] = "mac";
PLATFORM_IDS[3] = "solaris_sparc";
PLATFORM_IDS[4] = "solaris_x86";
PLATFORM_IDS[5] = "windows";

PLATFORM_LONG_NAMES[0] = "By Platform";
PLATFORM_LONG_NAMES[1] = "Linux (x86/x64)";
PLATFORM_LONG_NAMES[2] = "Mac OS X";
PLATFORM_LONG_NAMES[3] = "Solaris SPARC";
PLATFORM_LONG_NAMES[4] = "Solaris x86";
PLATFORM_LONG_NAMES[5] = "Windows";

var OMNITURE_CODE_JS = "http://www.netbeans.org/images/js/s_code_remote.js";
var GOOGLE_ANALYTICS_JS = "http://www.google-analytics.com/ga.js";

function write_platforms() {
   for(var i=0; i < PLATFORM_IDS.length; i++ ) {
   	document.write('<option value="' + PLATFORM_IDS[i] + '">' + PLATFORM_LONG_NAMES[i] + '</option>');
   }
}

function detect_platform() {
    var agent = navigator.userAgent;

    if (agent.indexOf("Linux") != -1) {
        platformSelectIndex = 1;
    }
    if (agent.indexOf("Mac OS") != -1) {
        platformSelectIndex = 2;
    }
    if (agent.indexOf("SunOS sun4") != -1) {
        platformSelectIndex = 3;
	}
    if (agent.indexOf("SunOS i86pc") != -1) {
        platformSelectIndex = 4;
    }
    if (agent.indexOf("Windows") != -1) {
        platformSelectIndex = 5;
    }

	platformId = PLATFORM_IDS[platformSelectIndex];
	platformName = PLATFORM_LONG_NAMES[platformSelectIndex];
}

