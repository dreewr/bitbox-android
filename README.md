import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TagViewsWithIdRecursivelyTest {

    private val context: Context = spyk(ApplicationProvider.getApplicationContext<Context>())

    @Test
    fun `test tagViewsWithIdRecursively sets tag to match view id`() {
        // Mock the static method `getResourceEntryName`
        mockkStatic("android.content.res.Resources") // Mocking static behavior of Android's Resources class
        every { context.resources.getResourceEntryName(any()) } answers { "mocked_id_${firstArg<Int>()}" }

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
