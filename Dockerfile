FROM gcr.io/distroless/base
COPY ./app.exe /
CMD ["/app.exe"]
