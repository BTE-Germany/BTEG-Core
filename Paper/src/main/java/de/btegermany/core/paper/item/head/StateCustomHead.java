package de.btegermany.core.paper.item.head;

public enum StateCustomHead implements CustomHead {

    BL_BB ("e5120de6afa301eea0dfb54753c16f18fa946fb4fe75b949895263038f42974c"),
    BL_BE ("e69deaf62fe2abc0566cc056181f1b39bd4077135891dc5c68da4759ebee0488"),
    BL_BW ("c9bcb3a5d71d81be0d20ea715860a1a818c9c25f474de38d6e7e9c6f26f2b973"),
    BL_BY ("bf79448a48f8fbac041a1b7d43c95de4e8b27aff7ed58f31309afd299e17f4b"),
    BL_HB ("f1927bf69a040d54a89fca48f0c06d5a6bf9a19496c38bbf9c05a694938f7981"),
    BL_HE ("e4167d50ec7b2eda09945f497e2eb65942c886a0f96fe09f2bf736f82d2d22a4"),
    BL_HH ("cc5fcfb1702ab8249c5d67eedb9da0def8e456d56ac9c0c426e2ca2c1c9172fe"),
    BL_MV ("7865a2958561f814bfef0b98f785f7d34d07a7ef204144acde5e4d710375f11c"),
    BL_NI ("c372bdf5bf6058e87477ae1de65d048bd0481dc4611845ea35902fd7efb690ae"),
    BL_NW ("73f1566a1dc2fba5d1e13faacff65fea4e6770e1f03bec7d2faef327c69f7c00"),
    BL_RP ("7534040df875c87dc19f8db36b7dcb9052bd147f8ca5baa4ab6cee44eb7adb6d"),
    BL_SH ("1c7472f3f38cb6542d16d324e65e6a16500b2021085932a2b32ec37732e1c2fd"),
    BL_SL ("6021dbd0144d908e2d0f87421cbafcfc57235604eacbf1a187beb47b3a98914d"),
    BL_SN ("c5f32dd2c0dd702f2937007b8ecd2e4309044350f66518f1d487f515e8bd6e54"),
    BL_ST("f8432ff7f40ed8dcd8d9c054d2181fdec594134e1bc60e3cb0fb9505cfd16617"),
    BL_TH ("a88d46648b76d54eb1f3a0a22d41cc7ad3672939a4ce01308dab74098751bab3");

    final String texture;

    StateCustomHead(String texture) {
        this.texture = texture;
    }

    @Override
    public String getTexture() {
        return texture;
    }
}
