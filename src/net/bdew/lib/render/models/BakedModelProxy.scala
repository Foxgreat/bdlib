/*
 * Copyright (c) bdew, 2013 - 2016
 * https://github.com/bdew/bdlib
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.lib.render.models

import java.util
import javax.vecmath.Matrix4f

import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType
import net.minecraft.client.renderer.block.model.{BakedQuad, IBakedModel, ItemCameraTransforms, ItemOverrideList}
import net.minecraft.util.EnumFacing
import net.minecraftforge.client.model.IPerspectiveAwareModel
import org.apache.commons.lang3.tuple.Pair

/**
  * Base class for wrappers around IBakedModel
  */
class BakedModelProxy(aBase: IBakedModel) extends IPerspectiveAwareModel {
  val base = ModelUtils.makePerspectiveAware(aBase)

  override def isBuiltInRenderer = base.isBuiltInRenderer
  override def isAmbientOcclusion = base.isAmbientOcclusion
  override def isGui3d = base.isGui3d

  override def getOverrides: ItemOverrideList = base.getOverrides

  override def getQuads(state: IBlockState, side: EnumFacing, rand: Long): util.List[BakedQuad] = base.getQuads(state, side, rand)

  override def getParticleTexture = base.getParticleTexture

  //noinspection ScalaDeprecation
  override def getItemCameraTransforms: ItemCameraTransforms = base.getItemCameraTransforms

  override def handlePerspective(cameraTransformType: TransformType): Pair[_ <: IBakedModel, Matrix4f] =
    Pair.of(this, base.handlePerspective(cameraTransformType).getRight)
}
