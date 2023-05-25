package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.*;
import com.codecool.dungeoncrawl.data.beauty.*;
import com.codecool.dungeoncrawl.data.objects.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.MARSHROCK);
                            map.addMonster(new Skeleton(cell));
                            break;
                        case 'g':
                            cell.setType(CellType.MARSHROCK);
                            map.addMonster(new Ghost(cell));
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Bat(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'j':
                            cell.setType(CellType.CLOSED);
                            break;
                        case 'n':
                            cell.setType(CellType.CAVE);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.GRASS);
                            new Ring(cell);
                            break;
                        case 'á':
                            cell.setType(CellType.MARSHROCK);
                            new Gholum(cell);
                            new Golum(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.MARSHROCK);
                            new Phial(cell);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Shelob(cell));
                            break;
                        case 'w':
                            cell.setType(CellType.BEAUTY);
                            new Gandalf(cell);
                            break;
                        case 'f':
                            cell.setType(CellType.FOREST);
                            break;
                        case 'h':
                            cell.setType(CellType.BEAUTY);
                            new HobbitHouse(cell);
                            break;
                        case 'q':
                            cell.setType(CellType.GRASS);
                            break;
                        case 'd':
                            cell.setType(CellType.BEAUTY);
                            new Rivendell(cell);
                            break;
                        case 'e':
                            cell.setType(CellType.DEADFOREST);
                            break;
                        case '-':
                            cell.setType(CellType.MARSHROCK);
                            break;
                        case 't':
                            cell.setType(CellType.BEAUTY);
                            new Bones(cell);
                            break;
                        case 'z':
                            cell.setType(CellType.BEAUTY);
                            new Web(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Apple(cell);
                            break;
                        case 'é':
                            cell.setType(CellType.MT_DOOM);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
