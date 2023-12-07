import { url } from 'inspector';
import env from './env.json';
import { URL } from 'url';

export const addressProvider = (protocol: Protocol): { host: string } => {
  const origin = window.location.origin;
  const host = asBackendHost(origin, protocol);
  return { host };
}

export enum Protocol {
  HTTPS,
  WS
}

const asBackendHost = (appHost: string, protocol: Protocol): string => {
  const { backendUrl } = env;
  var baseUrl = new URL(backendUrl);
  const protocolAsString = protocol === Protocol.HTTPS
    ? "https"
    : "ws";
  return `${protocolAsString}://${baseUrl.hostname}:${baseUrl.port}`
}

