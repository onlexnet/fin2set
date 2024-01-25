declare var Plaid: any;

export const startLink = function (linkTokenData: string) {
    const handler = Plaid.create({
      token: linkTokenData,
      onSuccess: async (publicToken: any, metadata: any) => {
        console.log(
          `I have a public token: ${publicToken} I should exchange this`
        );
        alert(`I have a public token: ${publicToken} I should exchange this`)
      },
      onExit: (err: any, metadata: any) => {
        console.log(
          `I'm all done. Error: ${JSON.stringify(err)} Metadata: ${JSON.stringify(
            metadata
          )}`
        );
      },
      onEvent: (eventName: any, metadata: any) => {
        console.log(`Event ${eventName}`);
      },
    });
    handler.open();
  };
  