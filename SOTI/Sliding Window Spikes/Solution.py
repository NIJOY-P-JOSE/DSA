def SLIDING_WINDOW_SPIKES(measurements, measurementsLength, m, tolerance):
    ans = 0
    ws = sum(measurements[:m])
    wsq = sum(x*x for x in measurements[:m])

    for i in range(measurementsLength-m+1):
        if i > 0:
            new = measurements[i+m-1]
            old = measurements[i-1]

            ws = ws - old + new
            wsq = wsq - old**2 + new**2

        pse = ((wsq/m) - (ws/m)**2) ** 0.5

        if pse > tolerance:
            ans += i + 1

    return ans
