package com.jamiedev.mod.client.models;

import com.jamiedev.mod.entities.BigBeakEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModel;

public class BigBeakModel<T extends BigBeakEntity> extends AnimalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "bigbeak"), "main");
    private final ModelPart root;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart feathers;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart tail;

    public BigBeakModel(ModelPart root) {
        this.root = root.getChild("root");
        this.rightLeg = root.getChild("rightLeg");
        this.leftLeg = root.getChild("leftLeg");
        this.rightWing = root.getChild("rightWing");
        this.leftWing = root.getChild("leftWing");
        this.feathers = root.getChild("feathers");
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
    }

    public static TexturedModelData getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(64, 20).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.5F, 7.0F, 0.0F));

        PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(64, 20).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 7.0F, 0.0F));

        PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(43, 37).mirror().addBox(-2.0F, -8.0F, 0.0F, 2.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 2.0F, -13.0F));

        PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(43, 37).addBox(0.0F, -8.0F, 0.0F, 2.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, -13.0F));

        PartDefinition feathers = partdefinition.addOrReplaceChild("feathers", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, -11.0F));

        PartDefinition cube_r1 = feathers.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(42, -2).addBox(0.0F, -2.0F, 0.0F, 0.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r2 = feathers.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, -2).addBox(0.0F, -2.0F, 0.0F, 0.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 64).addBox(-3.0F, -19.25F, -3.5F, 6.0F, 24.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(-2.0F, -21.25F, -16.5F, 4.0F, 9.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.75F, -12.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -7.5F, -10.5F, 10.0F, 15.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -2.5F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(78, 1).addBox(-5.0F, 0.0F, -2.0F, 10.0F, 6.0F, 15.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, -7.5F, 10.5F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        feathers.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
