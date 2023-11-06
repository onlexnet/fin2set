import { Protocol, addressProvider } from "./addressProvider";
import {expect, jest, test} from '@jest/globals';

test('resolve dev01', () => {
  const actual = addressProvider(Protocol.HTTP);
  expect(actual).toStrictEqual({ host: "http://localhost:8080"})
});


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

  it('resolve localhost', () => {
    const frontendUrl = 'http://localhost:3000';
    const actual = addressProvider(Protocol.HTTP);
    expect(actual).toStrictEqual({ host: "http://localhost:8080"})
  });
  
  it('resolve dev01', () => {
    const frontendUrl = 'https://dev01.fin2set.net';
    const actual = addressProvider(Protocol.HTTP);
    expect(actual).toStrictEqual({ host: "http://localhost:8080"})
  });

});