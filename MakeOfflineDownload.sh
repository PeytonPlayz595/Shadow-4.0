#!/bin/sh
java -cp "desktopRuntime/MakeOfflineDownload.jar:desktopRuntime/CompileEPK.jar" net.lax1dude.eaglercraft.v1_8.buildtools.workspace.MakeOfflineDownload "javascript/OfflineDownloadTemplate.html" "javascript/classes.js" "javascript/assets.epk" "javascript/Shadow_Client_Offline_en_US.html" "javascript/Shadow_Client_Offline.html" "javascript/lang"
