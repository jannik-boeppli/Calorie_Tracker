const CracoLessPlugin = require("craco-less");

module.exports = {
  plugins: [
    {
      plugin: CracoLessPlugin,
      options: {
        lessLoaderOptions: {
          lessOptions: {
            modifyVars: {
              "@primary-color": "@green-7",
              "@body-background": "#141414",
              "@layout-body-background": "#141414",
              "@card-background": "#434343",
              "@card-actions-background": "#434343",
              "@link-color": "rgba(0,0,0,0.85)",
              "@link-hover-color": "@green-10",
              "@layout-sider-background": "#8c8c8c",
              "@layout-sider-background-light": "#8c8c8c",
              "@menu-bg": "#8c8c8c",
              "@menu-item-active-bg": "@green-10",
              "@layout-trigger-background": "#bfbfbf",
              "@layout-trigger-color": "#00000073",
              "@menu-item-color": "#00000073",
            },
            javascriptEnabled: true,
          },
        },
      },
    },
  ],
};
