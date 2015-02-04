package com.chrissionair.tutorial.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World; // abv
import net.minecraft.world.gen.feature.WorldGenerator; // afd

/*
 * world.I = isRemote
 * world.a = getBlock
 * world.f = setBlock
 * -> alle ints in Block umschreiben
 * function a = generate
 * 
 * ausserdem: 
 * Kriterium, ob Baum wachsen kann, an 1.7 Standard anpassen
 * statt setBlock besser wie 1.7 setBlockAndNotifyAdequately
 *  */

public class WorldGenFNWillow extends WorldGenerator
{
  Block leaf;
  int leafmeta;
  int logmeta;
  int size;
  Block log;
  
  public WorldGenFNWillow(Block i, int j, Block l, int k, int s)
  {
    this.leaf = i;
    this.leafmeta = j;
    this.log = l;
    this.logmeta = k;
    this.size = s;
  }
  
  public boolean generate(World world, Random random, int i, int j, int k)
  {
    if (world.isRemote) {
      return false;
    }
    this.size += random.nextInt(6); // die Idee der einer Size ist super
    int i1 = i;
    int j1 = j;
    int k1 = k;
    
    // dieses Kriterium ändern nach 1.7 Standard
    Block met = world.getBlock(i, j, k);
    while ((met != Blocks.dirt) && (met != Blocks.grass) && (met != Blocks.stone))
    {
      j--;
      met = world.getBlock(i, j, k);
      if ((met == Blocks.water) || (met == Blocks.flowing_water) || (met == Blocks.stone)) {
        return false;
      }
    }
    
    j1 = j + 1;
    
    // Stamm 5 Block hoch
    for (int i2 = -1; i2 <= 1; i2++) {
      for (int k2 = -1; k2 <= 1; k2++)
      {
        world.setBlock(i1 + i2, j1 - 1, k1 + k2, Blocks.dirt, 0, 2);// unterhalb dirt setzen
        for (int k3 = 0; k3 <= 4; k3++) {
          world.setBlock(i1 + i2, j1 + k3, k1 + k2, this.log, this.logmeta, 2);
        }
      }
    }
    // und noch höher
    for (int g = 5; g <= 6 + this.size / 2; g++) {
      world.setBlock(i1, j1 + g, k1, this.log, this.logmeta, 3);// warum flag 3 ?
    }
    
    // die Äste
    mainBranch(world, random, i1 + 2, j1 + 4, k1 + 2, 1, 1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    mainBranch(world, random, i1 + 2, j1 + 4, k1, 1, 0, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    inner(world, random, i1 + 1, j1 + 5, k1 + 1, 1, 1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    inner(world, random, i1 + 1, j1 + 5, k1, 1, 0, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    innerInner(world, random, i1, j1 + 6 + this.size / 2, k1, 1, 1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    
    mainBranch(world, random, i1 + 2, j1 + 4, k1 - 2, 1, -1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    mainBranch(world, random, i1, j1 + 4, k1 - 2, 0, -1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    inner(world, random, i1 + 1, j1 + 5, k1 - 1, 1, -1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    inner(world, random, i1, j1 + 5, k1 - 1, 0, -1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    innerInner(world, random, i1, j1 + 6 + this.size / 2, k1, 1, -1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    
    mainBranch(world, random, i1 - 2, j1 + 4, k1 - 2, -1, -1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    mainBranch(world, random, i1 - 2, j1 + 4, k1, -1, 0, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    inner(world, random, i1 - 1, j1 + 5, k1 - 1, -1, -1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    inner(world, random, i1 - 1, j1 + 5, k1, -1, 0, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    innerInner(world, random, i1, j1 + 6 + this.size / 2, k1, -1, -1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    
    mainBranch(world, random, i1 - 2, j1 + 4, k1 + 2, -1, 1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    mainBranch(world, random, i1, j1 + 4, k1 + 2, 0, 1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    inner(world, random, i1 - 1, j1 + 5, k1 + 1, -1, 1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    inner(world, random, i1, j1 + 5, k1 + 1, 0, 1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    innerInner(world, random, i1, j1 + 6 + this.size / 2, k1, -1, 1, this.size, this.leaf, this.leafmeta, this.log, this.logmeta);
    return true;
  }
  
  // Hauptast, 2 Logs dick
  // die Richtungen sind immer iD und kD, wonach dann auch die Seitenäste die Richtungen zugewiesen bekommen a,b,c,d
  public static void mainBranch(World world, Random random, int i2, int j2, int k2, int iD, int kD, int size, Block leaf, int leafmeta, Block log, int logmeta)
  {
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    int bend = 0; // die Idee "bend" ist super!
    int pos = 2 * size + size / 2; // pos ist das äusserste Ende des Asts. super Idee!
    if ((kD == 1) && (iD == 0))
    {
      a = -1;
      b = 1;
      c = 1;
      d = 1;
    }
    if ((kD == 1) && (iD == -1))
    {
      a = -1;
      b = 0;
      c = 0;
      d = 1;
    }
    if ((kD == 0) && (iD == -1))
    {
      a = -1;
      b = 1;
      c = -1;
      d = -1;
    }
    if ((kD == -1) && (iD == -1))
    {
      a = -1;
      b = 0;
      c = 0;
      d = -1;
    }
    if ((kD == -1) && (iD == 0))
    {
      a = -1;
      b = -1;
      c = 1;
      d = -1;
    }
    if ((kD == -1) && (iD == 1))
    {
      a = 1;
      b = 0;
      c = 0;
      d = -1;
    }
    if ((kD == 0) && (iD == 1))
    {
      a = 1;
      b = 1;
      c = 1;
      d = -1;
    }
    if ((kD == 1) && (iD == 1))
    {
      a = 1;
      b = 1;
      c = -1;
      d = 1;
    }
    int ind = 0;
    while (ind < pos)
    {
      if (world.getBlock(i2, j2, k2).isAir(world, i2, j2, k2)) {} // ist hier überall vergessen worden
      world.setBlock(i2, j2, k2, log, logmeta, 2);
      world.setBlock(i2, j2 - 1, k2, log, logmeta, 2); // 2 logs dick
      if (iD == 0) {
        i2 = i2 + random.nextInt(3) - 1; // -1, 0, 1; quer
      }
      if (kD == 0) {
        k2 = k2 + random.nextInt(3) - 1;
      }
      if (kD == 1) {
        k2 += random.nextInt(2);
      }
      if (kD == -1) {
        k2 -= random.nextInt(2);
      }
      if (iD == 1) {
        i2 += random.nextInt(2);
      }
      if (iD == -1) {
        i2 -= random.nextInt(2);
      }
      if ((bend == 0) && (random.nextInt(3) == 0)) {
        j2 += 1;
      }
      // weil hier random 2 statt 3, biegt sich Ast am Ende mehr zum Boden .. genial :)
      if ((bend == 2) && (random.nextInt(2) == 0)) {
        j2 -= 1;
      }
      // Seitenäste
      if (random.nextInt(24) == 0)
      {
        secFlag(world, random, i2, j2, k2, a, b, size, leaf, leafmeta, log, logmeta);
        secFlag(world, random, i2, j2, k2, c, d, size, leaf, leafmeta, log, logmeta);
      }
      if (ind == pos / 3) {
        bend = 1;
      }
      if (ind == 2 * pos / 3) {
        bend = 2;
      }
      ind++;
      if (random.nextInt(4) > 0)
      {
        genWillowLeaves(world, random, i2, j2, k2, leaf, leafmeta);
        world.setBlock(i2, j2, k2, log, logmeta, 2);
      }
    }
  }
  
  // Seitenast
  public static void secFlag(World world, Random random, int i11, int j11, int k11, int iD, int kD, int size, Block leaf, int leafmeta, Block log, int logmeta)
  {
    int index0 = 0;
    while (index0 < 2 * size)
    {
      j11 = j11 + random.nextInt(3) - 1;// -1, 0, 1
      if (iD == 1) {
        i11 += random.nextInt(2);
      }
      if (iD == -1) {
        i11 -= random.nextInt(2);
      }
      if (iD == 0) {
        i11 = i11 + random.nextInt(3) - 1;
      }
      if (kD == 1) {
        k11 += random.nextInt(2);
      }
      if (kD == -1) {
        k11 -= random.nextInt(2);
      }
      if (kD == 0) {
        k11 = k11 + random.nextInt(3) - 1;
      }
      if (world.getBlock(i11, j11, k11).isAir(world, i11, j11, k11)) {}
      world.setBlock(i11, j11, k11, log, logmeta, 2);
      if (random.nextInt(4) > 0)
      {
        world.setBlock(i11, j11, k11, log, logmeta, 2);
        genWillowLeaves(world, random, i11, j11, k11, leaf, leafmeta);
      }
      index0++;
    }
  }
  
  // der Ast schräg nach oben, ohne Seitenäste, wie secFlag
  public static void inner(World world, Random random, int i3, int j3, int k3, int iD, int kD, int size, Block leaf, int leafmeta, Block log, int logmeta)
  {
    int pos = 2 * size;
    int index = 0;
    int jindex = 5; // begrenzt Höhe
    while ((index < pos) && (jindex < 14))
    {
      if (world.getBlock(i3, j3, k3).isAir(world, i3, j3, k3)) {}
      world.setBlock(i3, j3, k3, log, logmeta, 2);
      int rat = 1 + index / 4; // genial: je höher, desto niedriger die Chance auf Höhe!
      if (random.nextInt(rat) == 0)
      {
        j3++;
        jindex++;
      }
      if (iD == 0) {
        i3 = i3 + random.nextInt(3) - 1;
      }
      if (kD == 0) {
        k3 = k3 + random.nextInt(3) - 1;
      }
      if (kD == 1) {
        k3 += random.nextInt(2);
      }
      if (kD == -1) {
        k3 -= random.nextInt(2);
      }
      if (iD == 1) {
        i3 += random.nextInt(2);
      }
      if (iD == -1) {
        i3 -= random.nextInt(2);
      }
      index++;
      if (random.nextInt(4) > 0)
      {
        genWillowLeaves(world, random, i3, j3, k3, leaf, leafmeta);
        world.setBlock(i3, j3, k3, log, logmeta, 2);
      }
    }
  }
  
  // der verbliebene Hauptstamm (-stämmchen)
  public static void innerInner(World world, Random random, int i4, int j4, int k4, int iD, int kD, int size, Block leaf, int leafmeta, Block log, int logmeta)
  {
    int index1 = 0;
    int jindex1 = 6 + size / 2;
    while ((index1 < 2 * size + 1) && (jindex1 < 16))
    {
      world.setBlock(i4, j4, k4, log, logmeta, 2);
      j4++;
      jindex1++;
      if (random.nextInt(3) == 0)
      {
        if (iD == -1) {
          i4 -= 1;
        }
        if (iD == 1) {
          i4 += 1;
        }
      }
      if (random.nextInt(3) == 0)
      {
        if (kD == -1) {
          k4 -= 1;
        }
        if (kD == 1) {
          k4 += 1;
        }
      }
      index1++;
      if (random.nextInt(4) > 0)
      {
        genWillowLeaves(world, random, i4, j4, k4, leaf, leafmeta);
        world.setBlock(i4, j4, k4, log, logmeta, 2);
      }
    }
  }
  
  // leaves Gruppe, zu beachten der Ansatzpunkt für das Holz
  public static void genWillowLeaves(World world, Random random, int i3, int j3, int k3, Block leaf, int leafmeta)
  {
    world.setBlock(i3, j3 + 1, k3, leaf, leafmeta, 2);
    world.setBlock(i3, j3 + 2, k3, leaf, leafmeta, 2);
    for (int jh = 1; jh >= -2; jh--)
    {
      if (world.getBlock(i3, j3 + jh - 1, k3).isAir(world, i3, j3 + jh - 1, k3)) {
        world.setBlock(i3, j3 + jh - 1, k3, leaf, leafmeta, 2);
      }
      for (int x = -1; x <= 1; x++) {
        for (int y = -1; y <= 1; y++) {
          if (((x != 0 ? 1 : 0) | (y != 0 ? 1 : 0)) != 0) {
            if ((((Math.abs(x) != 1 ? 1 : 0) | (Math.abs(y) != 1 ? 1 : 0)) != 0) && (world.getBlock(i3 + x, j3 + jh, k3 + y).isAir(world, i3 + x, j3 + jh, k3 + y))) {
              world.setBlock(i3 + x, j3 + jh, k3 + y, leaf, leafmeta, 2);
            }
          }
        }
      }
    }
  }
}
