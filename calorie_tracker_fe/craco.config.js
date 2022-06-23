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
              "@card-background": "#434343",
              "@card-actions-background": "#434343",
              "@link-color": "rgba(0,0,0,0.85)",
              "@link-hover-color": "@green-10"
            },
            javascriptEnabled: true,
          },
        },
      },
    },
  ],
};
