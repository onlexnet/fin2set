export const addressProvider = (): { host: string } => {
    const origin = window.location.origin;
    const host = asBackendHost(origin);
    return { host };
  }
  
  const asBackendHost = (appHost: string): string => {
    return "http://localhost:8080";
  }
  