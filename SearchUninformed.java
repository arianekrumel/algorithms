import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeMap;

public class SearchUninformed {

    // Create notation of farmer, wolf, sheep and cabbage
    static int farmer = 0;
    static int wolf = 1;
    static int sheep = 2;
    static int cabbage = 3;

    static int[] goal = new int[] { 1, 1, 1, 1 };

    public static void search(char type, ArrayList<int[]> states,
            TreeMap<Integer, ArrayList<Integer>> transitionTable,
            TreeMap<Integer, Integer> heuristic) {

        int root = 0;
        LinkedList<int[]> frontier = new LinkedList<int[]>();
        LinkedList<Integer> explored = new LinkedList<Integer>();
        TreeMap<Integer, Integer> previous = new TreeMap<Integer, Integer>();

        int[] element = { root, 4 };
        frontier.add(element);
        previous.put(root, -1);

        // while frontier is not empty
        while (frontier.size() > 0) {
            int current = 0;
            if (type == 'a') {
                // Consider heuristic and path cost for A*
                current = getSmallest(frontier)[0];
            }
            if (type == 'b') {
                // Consider path cost for BFS
                current = frontier.pop()[0];
            }

            explored.add(current);
            if (Arrays.equals(goal, states.get(current))) {
                printPath(current, previous, states, true);
                return;
            }
            ArrayList<Integer> children = transitionTable.get(current);
            for (int i = 0; i < children.size(); i++) {
                int child = children.get(i);
                if (frontier.indexOf(child) == -1
                        && explored.indexOf(child) == -1) {
                    previous.put(child, current);
                    element[0] = child;
                    if (type == 'a') {
                        element[1] = cost(child, states, previous, heuristic);
                    } else {
                        element[1] = 0;
                    }
                    frontier.add(element);
                }
            }
        }
        return;
    }

    public static int[] getSmallest(LinkedList<int[]> frontier) {
        int[] current = frontier.pop();
        int[] temp = {};

        for (int i = 0; i < frontier.size(); i++) {
            int[] compare = frontier.pop();
            if (current[1] > compare[1]) {
                temp = current;
                current = compare;
                compare = temp;
            }
            frontier.add(compare);
        }

        return current;
    }

    public static int cost(int child, ArrayList<int[]> states,
            TreeMap<Integer, Integer> explored,
            TreeMap<Integer, Integer> heuristic) {
        int cost = 0;

        // get path cost
        cost += printPath(0, explored, states, false);

        // add h(n)
        cost += heuristic.get(child);

        return cost;
    }

    public static int printPath(int goal, TreeMap<Integer, Integer> previous,
            ArrayList<int[]> states, boolean print) {
        int current = goal;
        LinkedList<Integer> shortestPath = new LinkedList<Integer>();
        while (current != -1) {
            shortestPath.addFirst(current);
            current = previous.get(current);
        }
        for (int i = 0; i < shortestPath.size(); i++) {
            if (print) {
                System.out.print("\nstep " + i + ": ");
                outputNice(shortestPath.get(i), states);
            }
        }
        return shortestPath.size() - 1;
    }

    public static void outputNice(int stateIndex, ArrayList<int[]> states) {
        int[] state = states.get(stateIndex);
        for (int i = 0; i < 2; i++) {
            if (state[farmer] == i) {
                System.out.print("f ");
            } else {
                System.out.print("_ ");
            }
            if (state[wolf] == i) {
                System.out.print("w ");
            } else {
                System.out.print("_ ");
            }
            if (state[sheep] == i) {
                System.out.print("s ");
            } else {
                System.out.print("_ ");
            }
            if (state[cabbage] == i) {
                System.out.print("c ");
            } else {
                System.out.print("_ ");
            }
            if (i == 0) {
                System.out.print("| ");
            }
        }
    }
}
