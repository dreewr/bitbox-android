class TagViewsWithIdRecursivelyTest {

    private val context: Context = spy(ApplicationProvider.getApplicationContext<Context>())

    @Test
    fun `test tagViewsWithIdRecursively sets tag to match view id`() {
        // Mock getResourceEntryName to return a fake name based on the ID
        doAnswer { invocation ->
            "mocked_id_${invocation.arguments[0]}"
        }.whenever(context.resources).getResourceEntryName(anyInt())

        // Create a root LinearLayout
        val rootLayout = LinearLayout(context).apply {
            id = View.generateViewId()
        }

        // Add child views with IDs
        val textView1 = TextView(context).apply { id = View.generateViewId() }
        val textView2 = TextView(context).apply { id = View.generateViewId() }
        rootLayout.addView(textView1)
        rootLayout.addView(textView2)

        // Assert initial state: tags are null
        assertNull(rootLayout.tag)
        assertNull(textView1.tag)
        assertNull(textView2.tag)

        // Call the extension function
        rootLayout.tagViewsWithIdRecursively()

        // Assert that tags now match mocked resource entry names
        assertEquals("mocked_id_${rootLayout.id}", rootLayout.tag)
        assertEquals("mocked_id_${textView1.id}", textView1.tag)
        assertEquals("mocked_id_${textView2.id}", textView2.tag)
    }
}
