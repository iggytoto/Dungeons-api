/**
 * Package contains dungeons data structures and dungeons instances
 * {@link org.gassangaming.model.dungeon.DungeonInstance} Contains general information about dungeon instance.
 * {@link org.gassangaming.model.dungeon.DungeonRoom} and {@link org.gassangaming.model.dungeon.DungeonPath} represents tree structure
 * of dungeons where room is a node and path is a edge.
 * Dungeons are visited by {@link org.gassangaming.model.dungeon.DungeonExpedition} a set of {@link  org.gassangaming.model.unit.Unit}
 * that enters the dungeon and moves by pathes between rooms.
 * While in the dungeon expedition may or may not trigger {@link org.gassangaming.model.dungeon.DungeonEvent} found
 * loot or encounter etc.
 */
package org.gassangaming.model.dungeon;