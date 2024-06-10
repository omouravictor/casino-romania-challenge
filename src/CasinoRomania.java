import java.util.Scanner;

public class CasinoRomania {

    private static final int CLOCKWISE = 0;
    private static final int COUNTER_CLOCKWISE = 1;

    public void play() {
        Scanner scanner = new Scanner(System.in);

        int n;
        int k;
        int m;

        while ((n = scanner.nextInt()) != 0) {
            k = scanner.nextInt();
            m = scanner.nextInt();

            PlayersCircularLinkedList players = new PlayersCircularLinkedList(n);
            Node playersInitialNode;
            Node kPlayerNode;
            Node mPlayerNode;
            Node auxK;
            Node auxM;

            playersInitialNode = players.getInitialNode();
            kPlayerNode = playersInitialNode;
            mPlayerNode = playersInitialNode.prev;

            while (players.getRemainingPlayers() > 2) {
                kPlayerNode = players.getPlayerInDirection(kPlayerNode, k, CLOCKWISE);
                mPlayerNode = players.getPlayerInDirection(mPlayerNode, m, COUNTER_CLOCKWISE);

                if (kPlayerNode.next == mPlayerNode)
                    auxK = mPlayerNode.next;
                else
                    auxK = kPlayerNode.next;

                if (mPlayerNode.prev == kPlayerNode)
                    auxM = kPlayerNode.prev;
                else
                    auxM = mPlayerNode.prev;

                if (kPlayerNode == mPlayerNode) {
                    players.removePlayer(kPlayerNode);
                    System.out.printf("%3d,", mPlayerNode.id);
                } else {
                    players.removePlayer(kPlayerNode);
                    players.removePlayer(mPlayerNode);
                    System.out.printf("%3d%3d,", kPlayerNode.id, mPlayerNode.id);
                }

                kPlayerNode = auxK;
                mPlayerNode = auxM;
            }

            if (players.getRemainingPlayers() == 2) {
                kPlayerNode = players.getPlayerInDirection(kPlayerNode, k, CLOCKWISE);
                mPlayerNode = players.getPlayerInDirection(mPlayerNode, m, COUNTER_CLOCKWISE);

                if (kPlayerNode == mPlayerNode)
                    System.out.printf("%3d,%3d\n", kPlayerNode.id, kPlayerNode.next.id);
                else
                    System.out.printf("%3d%3d\n", kPlayerNode.id, kPlayerNode.next.id);
            } else
                System.out.printf("%3d\n", playersInitialNode.id);
        }

        scanner.close();
    }

}