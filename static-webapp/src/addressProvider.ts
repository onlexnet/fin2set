export const addressProvider = (protocol: Protocol): { host: string } => {
    const origin = window.location.origin;
    const host = asBackendHost(origin, protocol);
    return { host };
  }
  
export enum Protocol {
    HTTP,
    WS
  }

  const asBackendHost = (appHost: string, protocol: Protocol): string => {
    // const parts = window.location.origin.split(':')
    // const addressWithoutPort = parts[0] + ":" + parts[1];
    // return `${addressWithoutPort}:8080`;
    // return 'https://polished-moderately-goblin.ngrok-free.app'
    switch (protocol) {
      case Protocol.HTTP:
        return 'http://localhost:8080';
      case Protocol.WS:  
        return 'ws://localhost:8080';
    }
  }

  