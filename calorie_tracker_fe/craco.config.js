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
              "@link-hover-color": "color(~`colorPalette('@{link-color}', 99) `);"
            },
            javascriptEnabled: true,
          },
        },
      },
    },
  ],
};
