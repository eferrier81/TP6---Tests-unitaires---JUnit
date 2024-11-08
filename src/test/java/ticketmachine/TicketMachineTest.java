package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		// Les montants ont été correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}

	@Test
	// S3 : On n’imprime pas le ticket si le montant inséré est insuffisant
	void printTicketInsufficientBalance() {
		machine.insertMoney(40); // Moins que le prix du ticket (50)
		assertFalse(machine.printTicket(), "Le ticket devrait pas être imprimé");
	}

	@Test
	// S4 : On imprime le ticket si le montant inséré est suffisant
	void printTicketSufficientBalance() {
		machine.insertMoney(50); // Exactement le prix du ticket
		assertTrue(machine.printTicket(), "Le ticket devrait être imprimé");
	}

	@Test
	// S5 : Quand on imprime un ticket, la balance est décrémentée du prix du ticket
	void balanceIsDecrementedAfterPrintingTicket() {
		machine.insertMoney(60); // Plus que le prix du ticket
		machine.printTicket();
		assertEquals(10, machine.getBalance(), "La balance n'a pas été correctement mise à jour après l'impression du ticket");
	}

	@Test
	// S6 : Le montant collecté est mis à jour quand on imprime un ticket
	void totalIsUpdatedAfterPrintingTicket() {
		machine.insertMoney(50); // Exactement le prix du ticket
		machine.printTicket();
		assertEquals(50, machine.getTotal(), "Le montant collecté n'a pas été mis à jour après l'impression du ticket");
	}

	@Test
	// S7 : refund() rend correctement la monnaie
	void refundReturnsCorrectAmount() {
		machine.insertMoney(100);
		int refundAmount = machine.refund();
		assertEquals(100, refundAmount, "Le montant remboursé est incorrect");
	}
	@Test
	// S8 : refund() remet la balance à zéro
	void refundResetsBalance() {
		machine.insertMoney(100);
		machine.refund();
		assertEquals(0, machine.getBalance(), "La balance n'a pas été remise à zéro après le remboursement");
	}

	@Test
	// S9 : On ne peut pas insérer un montant négatif
	void insertNegativeAmountThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> machine.insertMoney(-10), "L'insertion d'un montant négatif devrait lever une exception");
	}

	@Test
	void createMachineWithNegativePriceThrowsException() {
		// S10 : On ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
		assertThrows(IllegalArgumentException.class, () -> new TicketMachine(-50), "La création d'une machine avec un prix négatif devrait lever une exception");
	}
}
