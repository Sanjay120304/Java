import java.util.Random;

class Card {
    String representation;

    Card(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}

class Node {
    Card card;
    Node next;

    Node(Card card) {
        this.card = card;
        this.next = null;
    }
}

class LinkedList {
    private Node head;

    LinkedList() {
        head = null;
    }

    void addCard(Card card) {
        Node newNode = new Node(card);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    void shuffle() {
        // Conversion to array
        int size = size();
        Node[] nodes = new Node[size];
        Node current = head;
        int index = 0;

        while (current != null) {
            nodes[index++] = current;
            current = current.next;
        }

        // Shuffle array
        Random rand = new Random();
        for (int i = 0; i < nodes.length; i++) {
            int randomIndex = rand.nextInt(nodes.length);
            Node temp = nodes[i];
            nodes[i] = nodes[randomIndex];
            nodes[randomIndex] = temp;
        }

        // Rebuild linked list
        head = nodes[0];
        current = head;
        for (int i = 1; i < nodes.length; i++) {
            current.next = nodes[i];
            current = current.next;
        }
        current.next = null; 
    }

    int size() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    Card draw() {
        if (head == null) {
            return null; 
        }
        Card drawnCard = head.card;
        head = head.next;
        return drawnCard;
    }
}

public class CardGame { 
    public static void main(String[] args) {
        final int NUM_DECKS = 4;
        final int CARDS_PER_DECK = 52;
        final int CARDS_TO_DRAW = 13;

        LinkedList deck = new LinkedList();


        for (int i = 0; i < NUM_DECKS; i++) {
            for (int j = 1; j <= 13; j++) { 
                deck.addCard(new Card(j + "c")); 
                deck.addCard(new Card(j + "d")); 
                deck.addCard(new Card(j + "h")); 
                deck.addCard(new Card(j + "s")); 
            }
        }

        // Shuffle
        deck.shuffle();

        // Display 
        for (int i = 0; i < NUM_DECKS; i++) {
            System.out.println("Deck " + (i + 1) + ":");
            for (int j = 0; j < CARDS_TO_DRAW; j++) {
                System.out.print(deck.draw() + " ");
            }
            System.out.println("\n");
        }
    }
}
