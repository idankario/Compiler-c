SECTION .TEXT
GLOBAL _start
_start:
call main
mov eax, 1
xor ebx, ebx
int 0x80

main:
push rbp
mov rbp, rsp
mov rax, 4
push rax
pop rax
mov dword [rbp - 4], eax
mov eax, dword [rbp - 4]
push rax
mov rax, 4
push rax
pop rbx
pop rax
add rax, rbx
push rax
mov rsp, rbp
pop rbp
ret