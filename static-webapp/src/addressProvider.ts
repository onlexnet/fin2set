import env from './env.json';

export const addressProvider = (protocol: Protocol): { host: string } => {
  const origin = window.location.origin;
  const host = asBackendHost(origin, protocol);
  return { host };
}

export enum Protocol {
  HTTPS,
  WSS
}

const asBackendHost = (appHost: string, protocol: Protocol): string => {
  const { backendFQDN } = env;
  const protocolAsString = protocol === Protocol.HTTPS
    ? "https"
    : "wss";
  return `${protocolAsString}://${backendFQDN}`
}

