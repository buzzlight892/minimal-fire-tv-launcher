# Minimal Fire TV Launcher (starter project)

A small Java Android launcher prototype intended for older Fire TV devices.

## Compatibility
- Minimum Android API: 22 (Android 5.1 / commonly Fire OS 5)
- Target API: 28
- Java 17 build environment; Android Gradle Plugin 8.6.1
- Includes standard Android launcher, Android TV launcher, and HOME intent filters.

## Build in the cloud using GitHub Actions
1. Create a GitHub account if you do not already have one.
2. In Safari, open GitHub and create a **public or private repository** named `minimal-fire-tv-launcher`.
3. Upload the contents of this ZIP, preserving folder structure, to the repository (GitHub's web interface can upload files, though uploading many files on iPhone may be fiddly).
4. Open the repository's **Actions** tab and allow Actions if prompted.
5. Run **Build Minimal Fire TV Launcher** using **Run workflow**, or push to `main`.
6. Open the completed run and download the `minimal-fire-tv-launcher-debug` artifact. GitHub may package the artifact as a ZIP; extract it to get `app-debug.apk`.

## Important limitations
- This is a starter prototype, not a tested APK. It must successfully build and be tested on your exact Fire OS version.
- Android/Fire OS package visibility and icon behavior may differ by version.
- The HOME intent filter does not guarantee the Fire TV Home button will use this launcher. Amazon may block changing the default home app.
- Do not install APKs from untrusted sources. This starter project does not request internet access or collect data.
