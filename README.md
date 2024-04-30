# Shadow-4.0

**Go to [Github actions](./actions) for the latest builds**

## Building the web/offline version

I recommend that you fork this repository and build using Github Actions.

Here is how you build it yourself:

1. Clone this github repo
2. cd into the directory of this repo
3. Run `bash ./CompileJS.sh`
5. If you want the offline download, run `bash ./MakeOfflineDownload.sh`

## Running the Desktop Runtime

**Note: Requires a physical display to run, will NOT run on codespaces, ANY online terminal without a graphical display, or ANY ssh without X11 forwarding (Pretty much self explanitory but for the people who didn't know)**

**If you STILL don't understand, it basically means you have to run it on YOUR personal computer. You most likely will not be able to use it in a browser VM without a graphical interface or over SSH**

1. Clone this Github repo
2. cd into the directory of this repo
3. Run `bash ./RunClient.sh` and wait for it to finish compiling
