package com.chrissionair.tutorial.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFNSequoia extends WorldGenerator
{
  Block leaf;
  int leafmeta;
  Block log;
  int logmeta;
  
  public WorldGenFNSequoia(Block l, int lm, Block i, int j)
  {
    this.leaf = l;
    this.leafmeta = lm;
    this.log = i;
    this.logmeta = j;
  }
  
  public boolean generate(World world, Random random, int i, int j, int k)
  {
    if (world.isRemote) {
      return false;
    }
    int l = random.nextInt(23) + 16;// Höhe
    int ls = l - 4;
    
    int byte0 = 1;
    int iin = i;
    int kin = k;
    int sub = 1;
    int flag1 = 0;
    int center = 0;
    if (random.nextInt(4) == 0)
    {
      byte0 = 1;
      if (random.nextInt(3) == 0) {
        byte0 = 2;
      }
    }
    else
    {
      flag1 = 1;
    }
    int byte1 = byte0;
    Block met = world.getBlock(i + 1, j, k);
    if (((met == Blocks.water ? 1 : 0) | (met == Blocks.flowing_water ? 1 : 0)) != 0) {
      return false;
    }
    met = world.getBlock(i, j, k);
    while (((met != Blocks.dirt ? 1 : 0) & (met != Blocks.grass ? 1 : 0)) != 0)
    {
      j--;
      met = world.getBlock(i, j, k);
      if ((met == Blocks.water) || (met == Blocks.flowing_water) || (met == Blocks.stone)) {
        return false;
      }
    }
    if ((met != Blocks.grass) && (met != Blocks.dirt)) {
      return false;
    }
    int i1 = j + 1;
    if (flag1 == 0)// grosse Bäume
    {
      for (int j1 = -byte0; j1 <= byte0; j1++) {
        for (int j2 = -byte0; j2 <= byte0; j2++)
        {
          if (j1 == byte0) {
            world.setBlock(iin + j1, i1, kin + j2, Blocks.air, 0, 2);// warum das?
          }
          world.setBlock(iin + j1, i1 - 1, kin + j2, Blocks.dirt, 0, 2);
          for (int k3 = 0; k3 <= l; k3++) {
            world.setBlock(iin + j1, i1 + k3, kin + j2, this.log, this.logmeta, 2);
          }
          if ((byte0 == Math.abs(j1)) && (byte0 == Math.abs(j2)) && (byte0 != 1)) {
            for (int j3 = l; j3 >= 0; j3--)
            {
              world.setBlock(iin + j1, i1 + j3, kin + j2, Blocks.air, 0, 2);
              if ((j3 >= l / 2) && (world.getBlock(iin + j1, i1 + j3, kin + j2).isAir(world, iin + j1, i1 + j3, kin + j2))) {
                world.setBlock(iin + j1, i1 + j3, kin + j2, this.leaf, this.leafmeta, 2);
              }
            }
          }
        }
      }
      
      // leaves aussenrum
      byte0 += 1;
      for (int k1 = -byte0; k1 <= byte0; k1++) {
        for (int k2 = -byte0; k2 <= byte0; k2++) {
          if ((byte0 == Math.abs(k1)) || (byte0 == Math.abs(k2))) {
            for (int j4 = l / 2; j4 <= l; j4++)
            {
              // Fehler
              if ((random.nextInt(1) == 0) && (world.getBlock(iin + k1, i1 + j4, kin + k2).isAir(world, iin + k1, i1 + j4, kin + k2))) {}
              world.setBlock(iin + k1, i1 + j4, kin + k2, this.leaf, this.leafmeta, 2);
            }
          }
        }
      }
      for (int j3 = l / 2; j3 <= l - 3; j3++)
      {
        if (random.nextInt(2) == 0) {
          branches(world, random, iin - byte1, i1 + j3, kin, -1, 0, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(2) == 0) {
          branches(world, random, iin + byte1, i1 + j3, kin, 1, 0, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(2) == 0) {
          branches(world, random, iin, i1 + j3, kin - byte1, 0, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(2) == 0) {
          branches(world, random, iin, i1 + j3, kin + byte1, 0, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(2) == 0) {
          branches(world, random, iin - byte1, i1 + j3, kin + byte1, -1, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(2) == 0) {
          branches(world, random, iin - byte1, i1 + j3, kin - byte1, -1, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(2) == 0) {
          branches(world, random, iin + byte1, i1 + j3, kin + byte1, 1, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
        if (random.nextInt(2) == 0) {
          branches(world, random, iin + byte1, i1 + j3, kin - byte1, 1, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
        }
      }
      return true;
    }
    
    // flag = 1; kleine Bäume
    for (int j1 = -1; j1 <= 1 - sub; j1++) {
      for (int j2 = -1; j2 <= 1 - sub; j2++)
      {
        if (j1 == 1) {
          world.setBlock(iin + j1, i1, kin + j2, Blocks.air, 0, 2);
        }
        world.setBlock(iin + j1, i1 - 1, kin + j2, Blocks.dirt, 0, 2);
        world.setBlock(iin + j1, i1, kin + j2, this.log, this.logmeta, 2);
        for (int k3 = 1; k3 <= ls; k3++) {
          world.setBlock(iin + j1, i1 + k3, kin + j2, this.log, this.logmeta, 2);
        }
      }
    }
    for (int j3 = l / 2; j3 <= l - 6; j3++)
    {
      if (random.nextInt(4) == 0) {
        branches(world, random, iin - 1, i1 + j3, kin, -1, 0, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (random.nextInt(4) == 0) {
        branches(world, random, iin, i1 + j3, kin, 1, 0, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (random.nextInt(4) == 0) {
        branches(world, random, iin, i1 + j3, kin - 1, 0, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (random.nextInt(4) == 0) {
        branches(world, random, iin, i1 + j3, kin, 0, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (random.nextInt(4) == 0) {
        branches(world, random, iin - 1, i1 + j3, kin, -1, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (random.nextInt(4) == 0) {
        branches(world, random, iin - 1, i1 + j3, kin - 1, -1, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (random.nextInt(4) == 0) {
        branches(world, random, iin, i1 + j3, kin, 1, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (random.nextInt(4) == 0) {
        branches(world, random, iin, i1 + j3, kin - 1, 1, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
    }
    return true;
  }
  
  public static void branches(World world, Random random, int ii, int jj, int kk, int iD, int kD, Block leaf, int leafmeta, Block log, int logmeta)
  {
    for (int br = 0; br < 9; br++)
    {
      if ((iD == -1) && (random.nextInt(3) == 0)) {
        ii--;
      }
      if ((iD == 1) && (random.nextInt(3) == 0)) {
        ii++;
      }
      if ((kD == -1) && (random.nextInt(3) == 0)) {
        kk--;
      }
      if ((kD == 1) && (random.nextInt(3) == 0)) {
        kk++;
      }
      if ((world.getBlock(ii, jj, kk).isAir(world, ii, jj, kk)) || (world.getBlock(ii, jj, kk).isLeaves(world, ii, jj, kk))) {
        world.setBlock(ii, jj, kk, log, logmeta, 2);
      }
      if ((br == 8) || (random.nextInt(6) == 0)) {
        genSeqLeaves(world, ii, jj, kk, leaf, leafmeta);
      }
      jj++;
    }
  }
  
  public static void genSeqLeaves(World world, int i3, int j3, int k3, Block leaf, int leafmeta)
  {
    for (int x = -3; x <= 3; x++) {
      for (int y = -3; y <= 3; y++)
      {
        if (((Math.abs(x) != 3) || (Math.abs(y) != 3)) && ((Math.abs(x) != 2) || (Math.abs(y) != 3)) && ((Math.abs(x) != 3) || (Math.abs(y) != 2))) {
          if (world.getBlock(i3 + x, j3, k3 + y).isAir(world, i3 + x, j3, k3 + y)) {
            world.setBlock(i3 + x, j3, k3 + y, leaf, leafmeta, 2);
          }
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
      }
    }
  }
}
