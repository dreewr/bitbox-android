# bitbox-android
Repositório para o aplicativo mobile do projeto BitBox

42JHVBWYLH3FQGZNQUMNT2INJHH4NNIXQI2DUW4D7CLEU246IJGQAAAA


fun saveImage(context: Context, bitmap: Bitmap) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        saveImageWithScopedStorage(context, bitmap)
    } else {
        // Handle older Android versions (permission checks needed)
        if (hasPermissionToGallery(context)) {
            saveImageLegacy(context, bitmap)
        } else {
            // Request permission code
        }
    }
}

private fun saveImageWithScopedStorage(context: Context, bitmap: Bitmap) {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "image_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }

    val uri: Uri? = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    uri?.let {
        context.contentResolver.openOutputStream(it)?.use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }
    }
}

private fun saveImageLegacy(context: Context, bitmap: Bitmap) {
    // Implementation for Android 9 and below where permissions are needed
}

private fun hasPermissionToGallery(context: Context): Boolean {
    return ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}
