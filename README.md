@Throws(Exception::class)
override suspend fun saveVirtualCardImage(bitmap: Bitmap) {
    withContext(Dispatchers.IO) { 
        val filename = "screenshot_${System.currentTimeMillis()}.png"
        val fos: OutputStream?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android 10+ (Scoped Storage)
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = imageUri?.let { resolver.openOutputStream(it) }
        } else {
            // Android 9 e abaixo (Fluxo legado)
            val imagesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            if (imagesDir?.exists() == false) {
                imagesDir.mkdirs()
            }
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)

            // Notifica a galeria sobre a nova imagem para Android 9 e abaixo
            MediaScannerConnection.scanFile(
                context,
                arrayOf(image.toString()),
                null,
                null
            )
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.PNG, QUALITY_COMPRESS, it)
        }
    }
}
