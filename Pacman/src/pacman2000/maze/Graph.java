// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Graph.java
// Download by http://www.codefans.net
package pacman2000.maze;

import java.io.PrintStream;
import java.util.Vector;

// Referenced classes of package pacman2000.maze:
//            Route, Coord, Label, Edge

public class Graph
{

    public Graph()
    {
        nodes = new Vector();
        edges = new Vector();
    }

    public Graph(int maze[][], int sizeX, int sizeY)
    {
        nodes = new Vector();
        edges = new Vector();
        arr = new int[sizeX][sizeY];
        for(int j = 0; j < sizeY; j++)
        {
            for(int i = 0; i < sizeX; i++)
                arr[i][j] = maze[i][j];

        }

        x = sizeX;
        y = sizeY;
        Graph graph = new Graph();
        int nodeNum = 0;
        for(int j = 0; j < y; j++)
        {
            for(int i = 0; i < x; i++)
                if(isNode(i, j))
                {
                    addNode(nodeNum);
                    arr[i][j] = 10 + nodeNum;
                    nodeNum++;
                }

        }

        int n = nodesSize();
        q0 = new Route[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
                q0[i][j] = new Route(10000, -1);

        }

        for(int j = 0; j < y; j++)
        {
            for(int i = 0; i < x; i++)
            {
                if(arr[i][j] < 10)
                    continue;
                int d = j;
                if(d < y - 1)
                {
                    d++;
                    if(arr[i][d] > 9)
                        addEdge(arr[i][j] - 10, arr[i][d] - 10, 1);
                    else
                    if(arr[i][d] == 1)
                    {
                        for(; arr[i][d] == 1; d++);
                        addEdge(arr[i][j] - 10, arr[i][d] - 10, d - j);
                    }
                }
                int r = i;
                if(r >= x - 1)
                    continue;
                r++;
                if(arr[r][j] > 9)
                {
                    addEdge(arr[i][j] - 10, arr[r][j] - 10, 1);
                    continue;
                }
                if(arr[r][j] != 1)
                    continue;
                for(; arr[r][j] == 1; r++);
                addEdge(arr[i][j] - 10, arr[r][j] - 10, r - i);
            }

        }

        q0[arr[0][y / 2] - 10][arr[x - 1][y / 2] - 10] = new Route(1, arr[x - 1][y / 2] - 10);
        q0[arr[x - 1][y / 2] - 10][arr[0][y / 2] - 10] = new Route(1, arr[0][y / 2] - 10);
        System.out.println("Initial distance matrix created");
        routes = q0;
        for(int i = 0; i < nodes.size(); i++)
            routes = nextLevel(routes, i);

    }

    private boolean isNode(int xPos, int yPos)
    {
        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;
        boolean node = false;
        if(arr[xPos][yPos] == 1 || arr[xPos][yPos] >= 10)
        {
            if(xPos - 1 >= 0 && arr[xPos - 1][yPos] != 2)
                left = true;
            if(xPos + 1 < x && arr[xPos + 1][yPos] != 2)
                right = true;
            if(yPos - 1 >= 0 && arr[xPos][yPos - 1] != 2)
                down = true;
            if(yPos + 1 < y && arr[xPos][yPos + 1] != 2)
                up = true;
            if((!right || !left || up || down) && (right || left || !up || !down))
                node = true;
        }
        return node;
    }

    private Coord nodePos(int nodenum)
    {
        for(int j = 0; j < y; j++)
        {
            for(int i = 0; i < x; i++)
                if(arr[i][j] == nodenum + 10)
                {
                    Coord c = new Coord(i, j);
                    return c;
                }

        }

        return null;
    }

    private void addNode(int thisLabel)
    {
        Label label = new Label();
        label.setLabel(thisLabel);
        nodes.addElement(label);
    }

    private void addEdge(int nodeA, int nodeB, int length)
    {
        Edge edge = new Edge(nodeA, nodeB, length);
        edges.addElement(edge);
        q0[nodeA][nodeB] = new Route(length, nodeB);
        q0[nodeB][nodeA] = new Route(length, nodeA);
    }

    private int nodeAt(int index)
    {
        Label label = (Label)nodes.elementAt(index);
        return label.getLabel();
    }

    private int nodesSize()
    {
        return nodes.size();
    }

    private int edgesSize()
    {
        return edges.size();
    }

    private boolean nodeExists(int number)
    {
        boolean exists = false;
        for(int i = 0; i < nodesSize(); i++)
        {
            int pos = nodeAt(i);
            if(pos == number)
                exists = true;
        }

        return exists;
    }

    private Route[][] nextLevel(Route qOld[][], int level)
    {
        int n = nodes.size();
        Route qNew[][] = new Route[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
                if(qOld[i][j].getDistance() < qOld[i][level].getDistance() + qOld[level][j].getDistance())
                    qNew[i][j] = new Route(qOld[i][j].getDistance(), qOld[i][j].getNextNode());
                else
                    qNew[i][j] = new Route(qOld[i][level].getDistance() + qOld[level][j].getDistance(), qOld[i][level].getNextNode());

        }

        return qNew;
    }

    public char newDir(char nowDir, int nowX, int nowY, int destX, int destY)
    {
        if(nowX == destX && nowY == destY)
            return 'f';
        if(arr[nowX][nowY] < 10)
            return nowDir;
        char corner = isCorner(nowX, nowY, nowDir);
        if(corner != 'x')
            return corner;
        int destNode = nearestNode(arr[nowX][nowY] - 10, destX, destY);
        if(destNode == arr[nowX][nowY] - 10)
        {
            if(destX > nowX)
                return 'r';
            if(destX < nowX)
                return 'l';
            if(destY > nowY)
                return 'd';
            if(destY < nowY)
                return 'u';
        } else
        {
            int nextNode = routes[arr[nowX][nowY] - 10][destNode].getNextNode();
            Coord c = nodePos(nextNode);
            if(c.getA() == x - 1 && nowX == 0)
                return 'l';
            if(c.getA() == 0 && nowX == x - 1)
                return 'r';
            if(c.getA() > nowX)
                return 'r';
            if(c.getA() < nowX)
                return 'l';
            if(c.getB() > nowY)
                return 'd';
            if(c.getB() < nowY)
                return 'u';
        }
        return 'e';
    }

    private int nearestNode(int nowNode, int targX, int targY)
    {
        if(arr[targX][targY] > 9)
            return arr[targX][targY] - 10;
        if(targX != 0 && targX != x && arr[targX - 1][targY] != 2)
        {
            int x1 = targX;
            int x2 = targX;
            for(; arr[x1][targY] == 1; x1--);
            for(; arr[x2][targY] == 1; x2++);
            int x1dist = Math.abs(x1 - targX);
            int x2dist = Math.abs(x2 - targX);
            if(routes[nowNode][arr[x1][targY] - 10].getDistance() + x1dist < routes[nowNode][arr[x2][targY] - 10].getDistance() + x2dist)
                return arr[x1][targY] - 10;
            else
                return arr[x2][targY] - 10;
        }
        if(targY != 0 && targY != y)
        {
            int y1 = targY;
            int y2 = targY;
            for(; arr[targX][y1] == 1; y1--);
            for(; arr[targX][y2] == 1; y2++);
            int y1dist = Math.abs(y1 - targY);
            int y2dist = Math.abs(y2 - targY);
            if(routes[nowNode][arr[targX][y1] - 10].getDistance() + y1dist < routes[nowNode][arr[targX][y2] - 10].getDistance() + y2dist)
                return arr[targX][y1] - 10;
            else
                return arr[targX][y2] - 10;
        } else
        {
            return -1;
        }
    }

    private char isCorner(int nowX, int nowY, char nowDir)
    {
        return 'x';
    }

    private Vector nodes;
    private Vector edges;
    private int x;
    private int y;
    private int arr[][];
    private Route q0[][];
    private Route routes[][];
}
