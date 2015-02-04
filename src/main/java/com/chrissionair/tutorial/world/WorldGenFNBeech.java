package com.chrissionair.tutorial.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFNBeech extends WorldGenerator
{
  Block leaf;
  int leafmeta;
  Block log;
  int logmeta;
  
  public WorldGenFNBeech(Block i, int j, Block k, int l)
  {
    this.leaf = i;
    this.leafmeta = j;
    this.log = k;
    this.logmeta = l;
  }
  
  public boolean generate(World world, Random random, int i, int j, int k)
  {
    if (world.isRemote) {
      return false;
    }
    int i1 = i;
    int j1 = j;
    int k1 = k;
    Block met = world.getBlock(i, j, k);
    while (((met != Blocks.dirt ? 1 : 0) & (met != Blocks.grass ? 1 : 0)) != 0)
    {
      j--;
      met = world.getBlock(i, j, k);
      if (((met == Blocks.water ? 1 : 0) | (met == Blocks.flowing_water ? 1 : 0) | (met == Blocks.stone ? 1 : 0)) != 0) {
        return false;
      }
    }
    j1 = j + 1;
    
    int h = random.nextInt(15) + 6;// die maxHöhe
    world.setBlock(i1, j1 - 1, k1, Blocks.dirt, 0, 2);
    for (int jj = 0; jj <= h; jj++)
    {
      world.setBlock(i1, j1 + jj, k1, this.log, this.logmeta, 2);// der Stamm
      if (jj == h) {
        leafGen(world, i1, j1 + jj, k1, this.leaf, this.leafmeta);
      }
      if (((jj > 3 ? 1 : 0) & (jj < h ? 1 : 0)) != 0)
      {
        int hl = h / jj + 1;// je höher desto kleiner die Zahl, desto grösser die Chance auf branch
        // branch wieder alle 8 Richtungen
        if (random.nextInt(hl) == 0) {
          branch(world, random, h, i1, j1, jj, k1, -1, 0, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(hl) == 0) {
          branch(world, random, h, i1, j1, jj, k1, 1, 0, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(hl) == 0) {
          branch(world, random, h, i1, j1, jj, k1, 0, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(hl) == 0) {
          branch(world, random, h, i1, j1, jj, k1, 0, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(hl) == 0) {
          branch(world, random, h, i1, j1, jj, k1, -1, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(hl) == 0) {
          branch(world, random, h, i1, j1, jj, k1, -1, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(hl) == 0) {
          branch(world, random, h, i1, j1, jj, k1, 1, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(hl) == 0) {
          branch(world, random, h, i1, j1, jj, k1, 1, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
      }
    }
    return true;
  }
  
  public static void branch(World world, Random random, int H, int ii, int j, int jj, int kk, int iD, int kD, Block leaf, int leafmeta, Block log, int logmeta)
  {
    int index = 0;
    int lengthToGo = H - jj;// genau: maxHöhe - aktuelle relative Höhe
    jj += j;// jj wird absolut
    while (index <= lengthToGo)
    {
      // alle 8 Richtungen sind berücksichtigt
      if ((iD == -1) && (random.nextInt(3) > 0))
      {
        ii--;
        if ((kD == 0) && (random.nextInt(4) == 0)) {
          kk = kk + random.nextInt(3) - 1;
        }
      }
      if ((iD == 1) && (random.nextInt(3) > 0))
      {
        ii++;
        if ((kD == 0) && (random.nextInt(4) == 0)) {
          kk = kk + random.nextInt(3) - 1;
        }
      }
      if ((kD == -1) && (random.nextInt(3) > 0))
      {
        kk--;
        if ((iD == 0) && (random.nextInt(4) == 0)) {
          ii = ii + random.nextInt(3) - 1;
        }
      }
      if ((kD == 1) && (random.nextInt(3) > 0))
      {
        kk++;
        if ((iD == 0) && (random.nextInt(4) == 0)) {
          ii = ii + random.nextInt(3) - 1;
        }
      }
      // hier ist ein Fehler: nur jj, nicht +j !, weil jj schon absolut ist
      if ((world.getBlock(ii, j + jj, kk).isAir(world, ii, j + jj, kk)) || (world.getBlock(ii, j + jj, kk).isLeaves(world, ii, j + jj, kk))) {
        world.setBlock(ii, jj, kk, log, logmeta, 2);
      }
      if (random.nextInt(3) == 0) {
        leafGen(world, ii, jj, kk, leaf, leafmeta);
      }
      if (random.nextInt(3) > 0) {// Höhenwachstum 2:3 
        jj++;
      }
      if (index == lengthToGo)// top Krone
      {
        world.setBlock(ii, jj, kk, log, logmeta, 2);
        leafGen(world, ii, jj, kk, leaf, leafmeta);
      }
      index++;
    }
  }
  
  // Krone 5 hoch, max 7 breit
  public static void leafGen(World world, int i3, int j3, int k3, Block leaf, int leafmeta)
  {
    for (int x = -3; x <= 3; x++) {
      for (int y = -3; y <= 3; y++)
      {
        if (((Math.abs(x) != 3) || (Math.abs(y) != 3)) && ((Math.abs(x) != 2) || (Math.abs(y) != 3)) && ((Math.abs(x) != 3) || (Math.abs(y) != 2)) && (world.getBlock(i3 + x, j3, k3 + y).isAir(world, i3 + x, j3, k3 + y))) {
          world.setBlock(i3 + x, j3, k3 + y, leaf, leafmeta, 2);
        }
        if ((Math.abs(x) < 3) && (Math.abs(y) < 3) && ((Math.abs(x) != 2) || (Math.abs(y) != 2)))
        {
          if (world.getBlock(i3 + x, j3 + 1, k3 + y).isAir(world, i3 + x, j3 + 1, k3 + y)) {
            world.setBlock(i3 + x, j3 + 1, k3 + y, leaf, leafmeta, 2);
          }
          if (world.getBlock(i3 + x, j3 - 1, k3 + y).isAir(world, i3 + x, j3 - 1, k3 + y)) {
            world.setBlock(i3 + x, j3 - 1, k3 + y, leaf, leafmeta, 2);
          }
        }
        if (Math.abs(x) + Math.abs(y) < 2)
        {
          if (world.getBlock(i3 + x, j3 + 2, k3 + y).isAir(world, i3 + x, j3 + 2, k3 + y)) {
            world.setBlock(i3 + x, j3 + 2, k3 + y, leaf, leafmeta, 2);
          }
          if (world.getBlock(i3 + x, j3 - 2, k3 + y).isAir(world, i3 + x, j3 - 2, k3 + y)) {
            world.setBlock(i3 + x, j3 - 2, k3 + y, leaf, leafmeta, 2);
          }
        }
      }
    }
  }
}
