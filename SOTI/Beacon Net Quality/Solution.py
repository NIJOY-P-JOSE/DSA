import math

def BEACONS_NET_QUALITY(length, beacons, beaconsLength, extraBeacons):
    beacons = sorted(beacons)

    points = [0] + beacons + [length]

    gap = []

    for i in range(1, len(points)):
        gap.append(points[i] - points[i - 1])

    low = 0
    high = length

    for _ in range(100):
        mid = (high + low) / 2

        if mid == 0:
            break

        need = 0

        for g in gap:
            need += max(0, math.ceil(g / mid) - 1)

        if need <= extraBeacons:
            high = mid
        else:
            low = mid

    return round((high / 2) * 1000)
