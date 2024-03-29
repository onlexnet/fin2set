import { Protocol, addressProvider } from "./addressProvider";
import {expect, jest, test} from '@jest/globals';

describe('resolve backend address', () => {
  let originalWindowLocation = window.location;

  beforeEach(() => {
    Object.defineProperty(window, 'location', {
      configurable: true,
      enumerable: true,
      value: new URL(window.location.href),
    });
  });

  afterEach(() => {
    Object.defineProperty(window, 'location', {
      configurable: true,
      enumerable: true,
      value: originalWindowLocation,
    });
  });

  it('resolve https', () => {
    const actual = addressProvider(Protocol.HTTPS);
    expect(actual).toStrictEqual({ host: "https://localhost"})
  });
  
  it('resolve ws', () => {
    const actual = addressProvider(Protocol.WSS);
    expect(actual).toStrictEqual({ host: "wss://localhost"})
  });

});