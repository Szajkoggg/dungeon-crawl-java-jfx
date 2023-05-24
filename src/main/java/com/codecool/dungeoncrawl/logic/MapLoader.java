package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.*;
import com.codecool.dungeoncrawl.data.objects.Gholum;
import com.codecool.dungeoncrawl.data.objects.Key;
import com.codecool.dungeoncrawl.data.objects.Phial;
import com.codecool.dungeoncrawl.data.objects.Sword;

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
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Skeleton(cell));
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
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
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'á':
                            cell.setType(CellType.FLOOR);
                            new Gholum(cell);
                            new Golum(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            new Phial(cell);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Shelob(cell));
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
