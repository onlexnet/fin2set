import { PublicToken } from "../../api/oas";

declare var Plaid: any;

export const startLink = async (linkToken: string): Promise<PublicToken> => {
    let onComplete: (value: PublicToken) => void;
    let onError: (reason?: any) => void;
    const result = new Promise<PublicToken>((onFulfilled, onRejected) => {
        onComplete = onFulfilled; onError = onRejected;
    });
      
    const handler = Plaid.create({
      token: linkToken,
      onSuccess: async (publicToken: string, metadata: any) => {
        console.log(
          `I have a public token: ${publicToken} I should exchange this`
        );
        onComplete({ publicToken });
      },
      onExit: (err: any, metadata: any) => {
        console.log(
          `I'm all done. Error: ${JSON.stringify(err)} Metadata: ${JSON.stringify(
            metadata
          )}`
        );
        onError(err);
      },
      onEvent: (eventName: any, metadata: any) => {
        console.log(`Event ${eventName}`);
      },
    });
    handler.open();

    return result;
  };
  