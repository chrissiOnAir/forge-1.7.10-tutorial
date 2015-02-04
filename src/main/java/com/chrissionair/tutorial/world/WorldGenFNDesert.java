package com.chrissionair.tutorial.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFNDesert extends WorldGenerator
{
  Block leaf = Blocks.leaves;
  int leafmeta;
  int logmeta;
  int i3;
  int j3;
  int k3;
  Block log = Blocks.log;
  
  public WorldGenFNDesert(int j, int f)
  {
    //this.leaf = i;
    this.leafmeta = j;
    //this.log = k;
    this.logmeta = f;
  }
  
  public boolean generate(World world, Random random, int i, int j, int k)
  {
    if (world.isRemote) {
      return false;
    }
    int i1 = i;
    int j1 = j;
    int k1 = k;
    int i2 = 0;
    int j2 = 0;
    int k2 = 0;
    
    Block met = world.getBlock(i, j, k);
    // dieses Kriterium verstehe ich nicht, weil es sich ja um Wasser unter dirt handeln kann
    // warum ist j-- ? weil es geht ja nur um die oberen Schichten
    // es geht also eigentlich immer nur 1 runter
    while (((met != Blocks.dirt ? 1 : 0) & (met != Blocks.grass ? 1 : 0) & (met != Blocks.sand ? 1 : 0)) != 0)
    {
      j--;
      met = world.getBlock(i, j, k);
      if (((met == Blocks.water ? 1 : 0) | (met == Blocks.flowing_water ? 1 : 0) | (met == Blocks.stone ? 1 : 0)) != 0) {
        return false;
      }
    }
    
    j1 = j + 1;
    int dir = random.nextInt(4);
    
    // hier werden zufällig die Abzweigungen für die Wurzeln gelegt
    // wieder 8 mal, weil 8 Richtungen
    if (random.nextInt(3) == 0) {
      rootGen(world, random, i, j, k, -1, 0, this.leaf, this.leafmeta, this.log, this.logmeta);
    }
    if (random.nextInt(3) == 0) {
      rootGen(world, random, i, j, k, 1, 0, this.leaf, this.leafmeta, this.log, this.logmeta);
    }
    if (random.nextInt(3) == 0) {
      rootGen(world, random, i, j, k, 0, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
    }
    if (random.nextInt(3) == 0) {
      rootGen(world, random, i, j, k, 0, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
    }
    if (random.nextInt(3) == 0) {
      rootGen(world, random, i, j, k, -1, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
    }
    if (random.nextInt(3) == 0) {
      rootGen(world, random, i, j, k, -1, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
    }
    if (random.nextInt(3) == 0) {
      rootGen(world, random, i, j, k, 1, 1, this.leaf, this.leafmeta, this.log, this.logmeta);
    }
    if (random.nextInt(3) == 0) {
      rootGen(world, random, i, j, k, 1, -1, this.leaf, this.leafmeta, this.log, this.logmeta);
    }
    rootGen(world, random, i, j, k, 0, 0, this.leaf, this.leafmeta, this.log, this.logmeta);
    
    // 4 Richtungen
    if (random.nextInt(10) < 9) // die kleine Size
    {
      if (dir == 0)
      {
        world.setBlock(i1, j1 - 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1 - 1, j1 - 1, k1, Blocks.dirt, 0, 2);
        world.setBlock(i1, j1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1 + 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1 - 1, j1, k1, this.log, this.logmeta, 2);
        largeDirect(world, random, 1, 0, i1 + i2, j1 + j2 + 1, k1, 1, 2, 0, 2, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (dir == 1)
      {
        world.setBlock(i1, j1 - 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1 - 1, k1 - 1, Blocks.dirt, 0, 2);
        world.setBlock(i1, j1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1 + 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1, k1 - 1, this.log, this.logmeta, 2);
        largeDirect(world, random, 0, 1, i1, j1 + j2 + 1, k1 + k2, 1, 2, 0, 2, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (dir == 2)
      {
        world.setBlock(i1, j1 - 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1 + 1, j1 - 1, k1, Blocks.dirt, 0, 2);
        world.setBlock(i1, j1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1 + 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1 + 1, j1, k1, this.log, this.logmeta, 2);
        largeDirect(world, random, -1, 0, i1 + i2, j1 + j2 + 1, k1, 1, 2, 0, 2, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (dir == 3)
      {
        world.setBlock(i1, j1 - 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1 - 1, k1 + 1, Blocks.dirt, 0, 2);
        world.setBlock(i1, j1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1 + 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1, k1 + 1, this.log, this.logmeta, 2);
        largeDirect(world, random, 0, -1, i1, j1 + j2, k1 + k2, 1, 2, 0, 2, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
    }
    else // 1:10 die grosse Size
    {
      if (dir == 0)
      {
        world.setBlock(i1, j1 - 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1 - 1, j1 - 1, k1, Blocks.dirt, 0, 2);
        world.setBlock(i1 + 1, j1 - 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1 + 1, j1, k1, this.log, this.logmeta, 2);
        largeDirect(world, random, 1, 0, i1 + i2, j1 + j2, k1, 2, 5, 4, 3, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (dir == 1)
      {
        world.setBlock(i1, j1 - 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1 - 1, k1 - 1, Blocks.dirt, 0, 2);
        world.setBlock(i1, j1 - 1, k1 + 1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1, k1 + 1, this.log, this.logmeta, 2);
        largeDirect(world, random, 0, 1, i1, j1 + j2, k1 + k2, 2, 5, 4, 3, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (dir == 2)
      {
        world.setBlock(i1, j1 - 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1 + 1, j1 - 1, k1, Blocks.dirt, 0, 2);
        world.setBlock(i1 - 1, j1 - 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1 - 1, j1, k1, this.log, this.logmeta, 2);
        largeDirect(world, random, -1, 0, i1 + i2, j1 + j2, k1, 2, 5, 4, 3, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
      if (dir == 3)
      {
        world.setBlock(i1, j1 - 1, k1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1 - 1, k1 + 1, Blocks.dirt, 0, 2);
        world.setBlock(i1, j1 - 1, k1 - 1, this.log, this.logmeta, 2);
        world.setBlock(i1, j1, k1 - 1, this.log, this.logmeta, 2);
        largeDirect(world, random, 0, -1, i1, j1 + j2, k1 + k2, 2, 5, 4, 3, this.leaf, this.leafmeta, this.log, this.logmeta);
      }
    }
    return true;
  }
  
  public static void branchAndLeaf(World world, int i3, int j3, int k3, Block leaf, int leafmeta, Block log, int logmeta)
  {
    world.setBlock(i3, j3, k3, log, logmeta, 2);
    for (int x = -3; x <= 3; x++) {
      for (int y = -3; y <= 3; y++)
      {
        if (((Math.abs(x) != 3) || (Math.abs(y) != 3)) && ((Math.abs(x) != 2) || (Math.abs(y) != 3)) && ((Math.abs(x) != 3) || (Math.abs(y) != 2)) && (world.getBlock(i3 + x, j3, k3 + y).getMaterial() == Material.air)) {
          world.setBlock(i3 + x, j3, k3 + y, leaf, leafmeta, 2);
        }
        if ((Math.abs(x) < 3) && (Math.abs(y) < 3) && ((Math.abs(x) != 2) || (Math.abs(y) != 2))) {
          world.setBlock(i3 + x, j3 + 1, k3 + y, leaf, leafmeta, 2);
        }
      }
    }
  }
  
  public static void largeDirect(World world, Random random, int iDir, int kDir, int i2, int j2, int k2, int size, int s1, int s2, int s3, Block leaf, int leafmeta, Block log, int logmeta)
  {
    int ss = 0;
    if (size == 2) {
      ss = 2;
    }
    for (int next = 0; next <= 5 * size; next++)
    {
      if (size == 1) {
        j2 += 1;
      }
      world.setBlock(i2, j2, k2, log, logmeta, 2);
      if ((next <= 9) && (size == 2)) {
        world.setBlock(i2 - iDir, j2, k2 - kDir, log, logmeta, 2);
      }
      if (next == 5 * size) {
        branchAndLeaf(world, i2, j2 + 1, k2, leaf, leafmeta, log, logmeta);
      }
      if (size == 2) {
        j2 += 1;
      }
      if (iDir == 1) {
        i2 += 1;
      } else if (iDir == -1) {
        i2 -= 1;
      } else if (kDir == 1) {
        k2 += 1;
      } else if (kDir == -1) {
        k2 -= 1;
      }
      if (next == s1) // die erste Abzweigung
      {
        int i3 = i2;
        int j3 = j2;
        int k3 = k2;
        for (int b = 0; b <= s1; b++)
        {
          if (iDir == 1)
          {
            if (random.nextInt(5) > 0) {
              i3 -= 1;
            }
            k3 += random.nextInt(2);
          }
          else if (iDir == -1)
          {
            if (random.nextInt(5) > 0) {
              i3 += 1;
            }
            k3 += random.nextInt(2);
          }
          if (kDir == 1)
          {
            i3 -= random.nextInt(2);
            if (random.nextInt(5) > 0) {
              k3 -= 1;
            }
          }
          else if (kDir == -1)
          {
            i3 += random.nextInt(2);
            if (random.nextInt(5) > 0) {
              k3 += 1;
            }
          }
          j3 += 1;
          world.setBlock(i3, j3, k3, log, logmeta, 2);
          if (b == s1) {
            branchAndLeaf(world, i3, j3, k3, leaf, leafmeta, log, logmeta);
          }
        }
        i3 = i2;
        j3 = j2;
        k3 = k2;
        for (int b = 0; b <= s1; b++) // die 2.Abzweigung auf selber Höhe
        {
          if (iDir == 1)
          {
            if (random.nextInt(5) > 0) {
              i3 -= 1;
            }
            k3 -= random.nextInt(2);
          }
          else if (iDir == -1)
          {
            if (random.nextInt(5) > 0) {
              i3 += 1;
            }
            k3 -= random.nextInt(2);
          }
          if (kDir == 1)
          {
            i3 += random.nextInt(2);
            if (random.nextInt(5) > 0) {
              k3 -= 1;
            }
          }
          else if (kDir == -1)
          {
            i3 -= random.nextInt(2);
            if (random.nextInt(5) > 0) {
              k3 += 1;
            }
          }
          j3 += 1;
          world.setBlock(i3, j3, k3, log, logmeta, 2);
          if (b == s1) {
            branchAndLeaf(world, i3, j3, k3, leaf, leafmeta, log, logmeta);
          }
        }
      }
      if ((next == 3 * size) && (size == 2)) // 2 neue Abzweigungen 1 höher, nur bei grosser size
      {
        int i3 = i2;
        int j3 = j2;
        int k3 = k2;
        for (int b = 0; b <= s2; b++)
        {
          if (iDir == 1)
          {
            i3 -= random.nextInt(2);
            k3 += random.nextInt(2);
          }
          if (iDir == -1)
          {
            i3 += random.nextInt(2);
            k3 += random.nextInt(2);
          }
          if (kDir == 1)
          {
            i3 -= random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (kDir == -1)
          {
            i3 += random.nextInt(2);
            k3 += random.nextInt(2);
          }
          j3 += 1;
          world.setBlock(i3, j3, k3, log, logmeta, 2);
          if (b == s2) {
            branchAndLeaf(world, i3, j3, k3, leaf, leafmeta, log, logmeta);
          }
        }
        i3 = i2;
        j3 = j2;
        k3 = k2;
        for (int b = 0; b <= s2; b++)
        {
          if (iDir == 1)
          {
            i3 -= random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (iDir == -1)
          {
            i3 += random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (kDir == 1)
          {
            i3 += random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (kDir == -1)
          {
            i3 -= random.nextInt(2);
            k3 += random.nextInt(2);
          }
          j3 += 1;
          world.setBlock(i3, j3, k3, log, logmeta, 2);
          if (b == s2) {
            branchAndLeaf(world, i3, j3, k3, leaf, leafmeta, log, logmeta);
          }
        }
      }
      if (next == 3 * size) // noch 2 Abzweigungen für beide Sizes
      {
        int i3 = i2;
        int j3 = j2;
        int k3 = k2;
        for (int b = 0; b <= 4 * size - ss; b++)
        {
          if (iDir == 1)
          {
            i3 += random.nextInt(2);
            k3 += random.nextInt(2);
          }
          if (iDir == -1)
          {
            i3 -= random.nextInt(2);
            k3 += random.nextInt(2);
          }
          if (kDir == 1)
          {
            i3 += random.nextInt(2);
            k3 += random.nextInt(2);
          }
          if (kDir == -1)
          {
            i3 += random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (b >= 3) {
            j3 += random.nextInt(2);
          }
          world.setBlock(i3, j3, k3, log, logmeta, 2);
          if (b == 4 * size - ss) {
            branchAndLeaf(world, i3, j3, k3, leaf, leafmeta, log, logmeta);
          }
        }
        i3 = i2;
        j3 = j2;
        k3 = k2;
        for (int b = 0; b <= 4 * size - ss; b++)
        {
          if (iDir == 1)
          {
            i3 += random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (iDir == -1)
          {
            i3 -= random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (kDir == 1)
          {
            i3 -= random.nextInt(2);
            k3 += random.nextInt(2);
          }
          if (kDir == -1)
          {
            i3 -= random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (b >= 3) {
            j3 += random.nextInt(2);
          }
          world.setBlock(i3, j3, k3, log, logmeta, 2);
          if (b == 4 * size - ss) {
            branchAndLeaf(world, i3, j3, k3, leaf, leafmeta, log, logmeta);
          }
        }
      }
      if (next == 4 * size) // und noch 2, am höchsten, mit kürzesten Ästen
      {
        int i3 = i2;
        int j3 = j2;
        int k3 = k2;
        for (int b = 0; b <= s3; b++)
        {
          if (iDir == 1)
          {
            i3 -= random.nextInt(2);
            k3 += random.nextInt(2);
          }
          if (iDir == -1)
          {
            i3 += random.nextInt(2);
            k3 += random.nextInt(2);
          }
          if (kDir == 1)
          {
            i3 -= random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (kDir == -1)
          {
            i3 += random.nextInt(2);
            k3 += random.nextInt(2);
          }
          j3 += 1;
          world.setBlock(i3, j3, k3, log, logmeta, 2);
          if (b == s3) {
            branchAndLeaf(world, i3, j3, k3, leaf, leafmeta, log, logmeta);
          }
        }
        i3 = i2;
        j3 = j2;
        k3 = k2;
        for (int b = 0; b <= s3; b++)
        {
          if (iDir == 1)
          {
            i3 -= random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (iDir == -1)
          {
            i3 += random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (kDir == 1)
          {
            i3 += random.nextInt(2);
            k3 -= random.nextInt(2);
          }
          if (kDir == -1)
          {
            i3 -= random.nextInt(2);
            k3 += random.nextInt(2);
          }
          j3 += 1;
          world.setBlock(i3, j3, k3, log, logmeta, 2);
          if (b == s3) {
            branchAndLeaf(world, i3, j3, k3, leaf, leafmeta, log, logmeta);
          }
        }
      }
    }
  }
  
  // die Wurzeln
  public static void rootGen(World world, Random random, int ii, int jj, int kk, int iD, int kD, Block leaf, int leafmeta, Block log, int logmeta)
  {
    for (int br = 0; br < 6; br++)
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
      Block met = world.getBlock(ii, jj, kk);
      if (((met.getMaterial() == Material.air ? 1 : 0) | (met == Blocks.grass ? 1 : 0) | (met == Blocks.dirt ? 1 : 0) | (met == Blocks.sand ? 1 : 0) | (met == Blocks.sandstone ? 1 : 0)) != 0) {
        world.setBlock(ii, jj, kk, log, logmeta, 2);
      }
      jj--;
    }
  }

}
