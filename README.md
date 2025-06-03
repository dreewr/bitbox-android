import io.mockk.*
import org.junit.Before
import org.junit.Test
import androidx.navigation.NavController
import androidx.navigation.NavDirections

class WalletNavigationTest {

    private val mockNavController = mockk<NavController>()
    private val mockWalletVO = mockk<WalletVO>()
    private val mockCardVO = mockk<CardVO>()
    private val mockCardDTO = mockk<CardDTO>()
    private val mockDeviceDTO = mockk<DeviceDTO>()
    private val mockTokenVO = mockk<TokenVO>()
    private val mockTokenOperation = mockk<TokenOperation>()

    private lateinit var walletNavigation: WalletNavigation

    @Before
    fun setup() {
        // Mock the safeNavigate extension function - this is the key workaround
        every { mockNavController.safeNavigate(any()) } just Runs
        every { mockNavController.popBackStack() } returns true
        
        walletNavigation = WalletNavigation(mockNavController)
    }

    // DecisionLoadingNavigation tests
    @Test
    fun `DecisionLoadingNavigation goToWalletSelection triggers navigation`() {
        // Given
        val wallets = arrayOf(mockWalletVO)

        // When
        walletNavigation.DecisionLoadingNavigation().goToWalletSelection(wallets)

        // Then - verify that navigation was triggered (black box approach)
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `DecisionLoadingNavigation goToCardSelection triggers navigation`() {
        // Given
        val cards = arrayOf(mockCardDTO)

        // When
        walletNavigation.DecisionLoadingNavigation().goToCardSelection(mockWalletVO, cards)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `DecisionLoadingNavigation goToTokenManagement triggers navigation`() {
        // Given
        val tokens = arrayOf(mockDeviceDTO)

        // When
        walletNavigation.DecisionLoadingNavigation().goToTokenManagement(mockWalletVO, mockCardVO, tokens)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `DecisionLoadingNavigation goToLandingPage triggers navigation`() {
        // When
        walletNavigation.DecisionLoadingNavigation().goToLandingPage(mockWalletVO, mockCardVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `DecisionLoadingNavigation goToNoCardsFoundError triggers navigation`() {
        // When
        walletNavigation.DecisionLoadingNavigation().goToNoCardsFoundError()

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `DecisionLoadingNavigation goToNoWalletsFoundError triggers navigation`() {
        // When
        walletNavigation.DecisionLoadingNavigation().goToNoWalletsFoundError()

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `DecisionLoadingNavigation goToGenericErrorScreen triggers navigation`() {
        // When
        walletNavigation.DecisionLoadingNavigation().goToGenericErrorScreen(mockWalletVO, mockCardVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    // TokenManagementNavigation tests
    @Test
    fun `TokenManagementNavigation goToSuccessScreen triggers navigation`() {
        // When
        walletNavigation.TokenManagementNavigation().goToSuccessScreen(mockWalletVO, mockCardVO, mockTokenOperation)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `TokenManagementNavigation goToErrorScreen triggers navigation`() {
        // When
        walletNavigation.TokenManagementNavigation().goToErrorScreen(mockWalletVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `TokenManagementNavigation goToPending triggers navigation`() {
        // When
        walletNavigation.TokenManagementNavigation().goToPending(mockWalletVO, mockCardVO, mockTokenVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    // WalletListNavigation tests
    @Test
    fun `WalletListNavigation openDecisionLoading triggers navigation`() {
        // When
        walletNavigation.WalletListNavigation().openDecisionLoading(mockWalletVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    // CardListNavigation tests
    @Test
    fun `CardListNavigation openDecisionLoading triggers navigation`() {
        // When
        walletNavigation.CardListNavigation().openDecisionLoading(mockWalletVO, mockCardVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    // ApprovePendingNavigation tests
    @Test
    fun `ApprovePendingNavigation goToSuccessScreen triggers navigation`() {
        // When
        walletNavigation.ApprovePendingNavigation().goToSuccessScreen(mockWalletVO, mockCardVO, mockTokenOperation)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `ApprovePendingNavigation goToErrorScreen triggers navigation`() {
        // When
        walletNavigation.ApprovePendingNavigation().goToErrorScreen(mockWalletVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    // ProvisioningErrorScreenNavigation tests
    @Test
    fun `ProvisioningErrorScreenNavigation goToInitialScreen triggers navigation`() {
        // When
        walletNavigation.ProvisioningErrorScreenNavigation().goToInitialScreen()

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    // GenericErrorScreenNavigation tests
    @Test
    fun `GenericErrorScreenNavigation goToInitialScreen triggers navigation`() {
        // When
        walletNavigation.GenericErrorScreenNavigation().goToInitialScreen(mockWalletVO, mockCardVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    // LandingPageNavigation tests
    @Test
    fun `LandingPageNavigation openCardProvisionSuccessScreen triggers navigation`() {
        // When
        walletNavigation.LandingPageNavigation().openCardProvisionSuccessScreen(mockWalletVO, mockCardVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `LandingPageNavigation openProvisionErrorScreen triggers navigation`() {
        // When
        walletNavigation.LandingPageNavigation().openProvisionErrorScreen(mockWalletVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    // SuccessEndingNavigation tests
    @Test
    fun `SuccessEndingNavigation goToInitialScreen triggers navigation`() {
        // When
        walletNavigation.SuccessEndingNavigation().goToInitialScreen(mockWalletVO, mockCardVO)

        // Then
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }

    // popBackStack tests
    @Test
    fun `popBackStack calls NavController popBackStack and executes action when provided`() {
        // Given
        val action = mockk<(Boolean) -> Unit>()
        every { action.invoke(any()) } just Runs

        // When
        walletNavigation.popBackStack(action)

        // Then
        verify(exactly = 1) { mockNavController.popBackStack() }
        verify(exactly = 1) { action.invoke(true) }
    }

    @Test
    fun `popBackStack calls NavController popBackStack without action`() {
        // When
        walletNavigation.popBackStack()

        // Then
        verify(exactly = 1) { mockNavController.popBackStack() }
    }

    @Test
    fun `multiple navigation calls result in multiple safeNavigate calls`() {
        // When
        walletNavigation.DecisionLoadingNavigation().goToWalletSelection(arrayOf(mockWalletVO))
        walletNavigation.TokenManagementNavigation().goToErrorScreen(mockWalletVO)
        walletNavigation.WalletListNavigation().openDecisionLoading(mockWalletVO)

        // Then
        verify(exactly = 3) { mockNavController.safeNavigate(any()) }
    }

    @Test
    fun `doAction method is indirectly tested through navigation methods`() {
        // This test verifies that the central doAction method works by testing it indirectly
        // through any navigation method (black box approach)
        
        // When
        walletNavigation.DecisionLoadingNavigation().goToNoCardsFoundError()

        // Then
        // If safeNavigate was called, it means doAction worked correctly
        verify(exactly = 1) { mockNavController.safeNavigate(any()) }
    }
}
