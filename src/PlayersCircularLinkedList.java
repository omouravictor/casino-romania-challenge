public class PlayersCircularLinkedList {

    private Node initialNode;

    public PlayersCircularLinkedList(int numberOfPlayers) {
        initialNode = createPlayersCircularLinkedNodes(numberOfPlayers);
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public int getRemainingPlayers() {
        int count = 1;
        Node currentPlayer = initialNode;
        while (initialNode != currentPlayer.next) {
            currentPlayer = currentPlayer.next;
            count++;
        }
        return count;
    }

    public Node getPlayerInDirection(Node playerReference, int n, int direction) {
        Node currentPlayer = playerReference;
        if (direction == 0)
            while (--n > 0)
                currentPlayer = currentPlayer.next;
        else
            while (--n > 0)
                currentPlayer = currentPlayer.prev;

        return currentPlayer;
    }

    public void removePlayer(Node player) {
        Node prevNode = player.prev;
        Node nextNode = player.next;

        if (player == initialNode) {
            initialNode = initialNode.next;
            prevNode.next = initialNode;
            initialNode.prev = player.prev;
        } else {
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
    }

    private Node createPlayersCircularLinkedNodes(int numberOfPlayers) {
        Node initialNode = null;
        Node lastNode = null;

        for (int number = 1; number <= numberOfPlayers; ++number) {
            Node currentPlayer = new Node(number);

            if (initialNode == null)
                initialNode = currentPlayer;
            else {
                lastNode.next = currentPlayer;
                currentPlayer.prev = lastNode;
            }

            lastNode = currentPlayer;
        }

        if (initialNode == null) return null;

        initialNode.prev = lastNode;
        lastNode.next = initialNode;

        return initialNode;
    }

}
