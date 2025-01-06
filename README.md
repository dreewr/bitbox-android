class TagViewsWithIdRecursivelyTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    @Test
    fun `test tagViewsWithIdRecursively sets tag to match view id`() {
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

        // Assert that tags now match resource entry names (IDs)
        assertEquals(context.resources.getResourceEntryName(rootLayout.id), rootLayout.tag)
        assertEquals(context.resources.getResourceEntryName(textView1.id), textView1.tag)
        assertEquals(context.resources.getResourceEntryName(textView2.id), textView2.tag)
    }
}
