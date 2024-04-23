#!/bin/sh
java -cp "desktopRuntime/MakeOfflineDownload.jar:desktopRuntime/CompileEPK.jar" net.lax1dude.eaglercraft.v1_8.buildtools.workspace.MakeOfflineDownload "javascript/OfflineDownloadTemplate.txt" "javascript/classes.js" "javascript/assets.epk" "/dev/null" "javascript/Shadow_Client_Offline.html" "javascript/lang"
