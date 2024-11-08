package ticketmachine;

public class TicketMachine {
	// Le prix du ticket de cette machine
	private final int price;
	// Le montant déjà inséré par le client
	private int balance;
	// Le montant total collecté par la machine
	private int total;

	/**
	 * Crée une machine qui délivre des tickets au prix spécifié.
	 *
	 * @param ticketCost le prix du ticket, doit être > 0
	 */
	public TicketMachine(int ticketCost) {
		if (ticketCost <= 0) {
			throw new IllegalArgumentException("Le prix du ticket doit être positif");
		}
		price = ticketCost;
		balance = 0;
		total = 0;
	}

	/**
	 * Retourne le prix d'un ticket.
	 *
	 * @return le prix des tickets de cette machine
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Retourne le montant total collecté par la machine.
	 *
	 * @return le montant total collecté
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * Retourne le montant déjà inséré pour le prochain ticket.
	 *
	 * @return le montant déjà inséré
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Permet d'insérer une somme d'argent en centimes.
	 *
	 * @param amount le montant inséré, en centimes (doit être positif)
	 * @throws IllegalArgumentException si le montant est inférieur ou égal à 0
	 */
	public void insertMoney(int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Le montant doit être positif");
		}
		balance += amount;
	}

	/**
	 * Effectue un remboursement et réinitialise la balance à zéro.
	 *
	 * @return le montant remboursé
	 */
	public int refund() {
		int refundAmount = balance;
		balance = 0;
		System.out.println("Je vous rends : " + refundAmount + " centimes");
		return refundAmount;
	}

	/**
	 * Imprime un ticket si le montant inséré est suffisant.
	 * Met à jour le total collecté et décrémente la balance du prix du ticket.
	 *
	 * @return vrai si le ticket a été imprimé, faux sinon
	 */
	public boolean printTicket() {
		if (balance >= price) {
			// Simuler l'impression du ticket
			System.out.println("##################");
			System.out.println("# The BlueJ Line");
			System.out.println("# Ticket");
			System.out.println("# " + price + " cents.");
			System.out.println("##################");
			System.out.println();

			// Mettre à jour la balance et le total
			balance -= price;
			total += price;
			return true;
		} else {
			System.out.println("Montant insuffisant pour imprimer le ticket");
			return false;
		}
	}
}
